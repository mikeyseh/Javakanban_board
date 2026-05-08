// File: Message.java
import javax.swing.JOptionPane;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a chat message with validation, hashing, and user action handling.
 */
public class Message {
    private String messageId;
    private String recipientNumber;
    private String messageText;
    private String messageHash;

    // Track user choices
    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();

    public Message(String messageId, String recipientNumber, String messageText) {
        this.messageId = messageId;
        this.recipientNumber = recipientNumber;
        this.messageText = messageText;
        this.messageHash = generateHash();
    }

    public boolean isValidId() {
        return messageId != null && messageId.length() <= 10;
    }

    public boolean isValidRecipient() {
        return recipientNumber != null
               && recipientNumber.length() == 10
               && recipientNumber.startsWith("0")
               && recipientNumber.chars().allMatch(Character::isDigit);
    }

    private String generateHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String combined = messageId + recipientNumber + messageText;
            byte[] digest = md.digest(combined.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "ERROR_HASH";
        }
    }

    private String formatForDisplay() {
        return  "Message ID:   " + messageId    + "\n"
              + "Message Hash: " + messageHash  + "\n"
              + "Recipient:    " + recipientNumber + "\n"
              + "Content:      " + messageText;
    }

    /**
     * Prompt user to send, store, or disregard this message.
     */
    public void chooseAction() {
        String[] options = {"Send", "Store", "Disregard"};
        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose an action for this message:",
            "Message Action",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        switch (choice) {
            case 0:
                sentMessages.add(this);
                JOptionPane.showMessageDialog(null, formatForDisplay(), "Message Sent", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                storedMessages.add(this);
                JOptionPane.showMessageDialog(null, formatForDisplay(), "Message Stored", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Message Disregarded.", "Discarded", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Returns unmodifiable list of sent messages.
     */
    public static List<Message> getSentMessages() {
        return Collections.unmodifiableList(sentMessages);
    }

    /**
     * Returns unmodifiable list of stored messages.
     */
    public static List<Message> getStoredMessages() {
        return Collections.unmodifiableList(storedMessages);
    }

    /**
     * Formats all sent messages into a single string.
     */
    public static String listSentMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append(m.formatForDisplay()).append("\n\n");
        }
        return sb.toString();
    }

    public static int totalSent() {
        return sentMessages.size();
    }
}


// File: JsonStorage.java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility to persist messages to JSON files using Gson.
 */
public class JsonStorage {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Saves a list of messages to the given filepath in JSON format.
     */
    public static void saveMessagesToJson(List<Message> messages, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            GSON.toJson(messages, writer);
            JOptionPane.showMessageDialog(
                null,
                "Successfully saved to " + filePath,
                "Save Complete",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error saving messages: " + e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}


// File: QuickChatApp.java
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Main application for QuickChat, providing login, menu, and message workflow.
 */
public class QuickChatApp {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        if (!authenticate()) {
            JOptionPane.showMessageDialog(null, "Too many failed attempts. Exiting.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.", "Greetings", JOptionPane.INFORMATION_MESSAGE);

        int choice;
        do {
            String input = JOptionPane.showInputDialog(
                "Select an option:\n"
              + "1) Compose Messages\n"
              + "2) Review Sent Messages\n"
              + "3) Quit"
            );
            if (input == null) break;
            choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> composeMessages();
                case 2 -> JOptionPane.showMessageDialog(
                                null,
                                Message.listSentMessages(),
                                "Sent Messages (" + Message.totalSent() + ")",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                case 3 -> JOptionPane.showMessageDialog(null, "Goodbye!", "Exit", JOptionPane.PLAIN_MESSAGE);
                default -> JOptionPane.showMessageDialog(null, "Invalid choice.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (choice != 3);
    }

    /**
     * Prompts for credentials up to MAX_LOGIN_ATTEMPTS.
     */
    private static boolean authenticate() {
        for (int i = 1; i <= MAX_LOGIN_ATTEMPTS; i++) {
            String user = JOptionPane.showInputDialog("Username:");
            String pass = JOptionPane.showInputDialog("Password:");
            if (USERNAME.equals(user) && PASSWORD.equals(pass)) {
                return true;
            }
            JOptionPane.showMessageDialog(null, "Login failed. Attempts left: " + (MAX_LOGIN_ATTEMPTS - i), "Auth Error", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    /**
     * Guides user through composing a series of messages, then persists them.
     */
    private static void composeMessages() {
        String countStr = JOptionPane.showInputDialog("How many messages would you like to process?");
        if (countStr == null) return;
        int total = Integer.parseInt(countStr);

        for (int i = 1; i <= total; i++) {
            // get valid ID
            String id;
            do {
                id = JOptionPane.showInputDialog("[" + i + "/" + total + "] Enter Message ID (max 10 chars):");
                if (id == null) return;
            } while (id.length() > 10);

            // get valid recipient
            String recipient;
            do {
                recipient = JOptionPane.showInputDialog("Enter Recipient Number (10 digits, starts with '0'):");
                if (recipient == null) return;
            } while (!recipient.matches("0\\d{9}"));

            // get content
            String text = JOptionPane.showInputDialog("Enter Message Content:");
            if (text == null) return;

            // build and process
            Message msg = new Message(id, recipient, text);
            msg.chooseAction();
        }

        // summary and persistence
        List<Message> sent = Message.getSentMessages();
        JsonStorage.saveMessagesToJson(sent, "sentMessages.json");

        JOptionPane.showMessageDialog(
            null,
            "Session complete. You sent " + sent.size() + " messages.",
            "Summary",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}