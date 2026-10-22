export default function Reports() {
  const logs = [
    { id: 'TSK-104', title: 'Fix authentication bug', dev: 'Elachim', date: 'Oct 22, 2026', status: 'Done' },
    { id: 'TSK-103', title: 'Design landing page', dev: 'Sarah', date: 'Oct 21, 2026', status: 'Done' },
    { id: 'TSK-102', title: 'Setup database schema', dev: 'John', date: 'Oct 20, 2026', status: 'Done' },
    { id: 'TSK-101', title: 'Initial commit', dev: 'Elachim', date: 'Oct 19, 2026', status: 'Done' },
  ]

  return (
    <div className="flex flex-col h-full">
      <header className="h-16 flex items-center px-8 border-b border-slate-800/60 bg-[#0f1115]/80 backdrop-blur-sm shrink-0">
        <h1 className="text-xl font-semibold text-white">Reports</h1>
      </header>
      <div className="flex-1 overflow-y-auto p-8">
        <div className="bg-[#13151a] border border-slate-800/50 rounded-2xl overflow-hidden">
          <table className="w-full text-left">
            <thead className="bg-[#0f1115] border-b border-slate-800/60">
              <tr>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Task ID</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Title</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Developer</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Date</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Status</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-800/50">
              {logs.map((log) => (
                <tr key={log.id} className="hover:bg-slate-800/20 transition-colors">
                  <td className="px-6 py-4 text-sm font-medium text-indigo-400">{log.id}</td>
                  <td className="px-6 py-4 text-sm text-slate-200">{log.title}</td>
                  <td className="px-6 py-4 text-sm text-slate-400">{log.dev}</td>
                  <td className="px-6 py-4 text-sm text-slate-400">{log.date}</td>
                  <td className="px-6 py-4">
                    <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-emerald-500/10 text-emerald-400 border border-emerald-500/20">
                      {log.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}