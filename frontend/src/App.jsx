import { Routes, Route, Navigate } from 'react-router-dom'
import Sidebar from './components/Sidebar'
import Board from './pages/Board'
import Overview from './pages/Overview'
import Projects from './pages/Projects'
import Analytics from './pages/Analytics'
import Reports from './pages/Reports'

export default function App() {
  return (
    <div className="flex h-screen overflow-hidden bg-[#0b0c10] text-slate-300">
      <Sidebar />
      <main className="flex-1 flex flex-col h-full overflow-hidden">
        <Routes>
          {/* Auto-redirect root to tasks */}
          <Route path="/" element={<Navigate to="/tasks" replace />} />
          
          <Route path="/tasks" element={<Board />} />
          <Route path="/overview" element={<Overview />} />
          <Route path="/projects" element={<Projects />} />
          <Route path="/analytics" element={<Analytics />} />
          <Route path="/reports" element={<Reports />} />
        </Routes>
      </main>
    </div>
  )
}