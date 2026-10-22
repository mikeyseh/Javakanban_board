export default function Overview() {
  const stats = [
    { label: 'Total Tasks', value: '24', color: 'text-indigo-400' },
    { label: 'In Progress', value: '8', color: 'text-amber-400' },
    { label: 'Completed', value: '16', color: 'text-emerald-400' },
    { label: 'Hours Logged', value: '142', color: 'text-purple-400' },
  ]

  return (
    <div className="flex flex-col h-full">
      <header className="h-16 flex items-center px-8 border-b border-slate-800/60 bg-[#0f1115]/80 backdrop-blur-sm shrink-0">
        <h1 className="text-xl font-semibold text-white">Overview</h1>
      </header>
      <div className="flex-1 overflow-y-auto p-8">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-8">
          {stats.map((stat, i) => (
            <div key={i} className="bg-[#13151a] border border-slate-800/50 p-6 rounded-2xl">
              <p className="text-sm text-slate-400 font-medium mb-2">{stat.label}</p>
              <h3 className={`text-3xl font-bold ${stat.color}`}>{stat.value}</h3>
            </div>
          ))}
        </div>
        <div className="bg-[#13151a] border border-slate-800/50 rounded-2xl p-6">
          <h2 className="text-lg font-semibold text-white mb-4">Recent Activity</h2>
          <div className="space-y-4">
            {[1, 2, 3].map((_, i) => (
              <div key={i} className="flex items-center gap-4 pb-4 border-b border-slate-800/50 last:border-0 last:pb-0">
                <div className="w-10 h-10 rounded-full bg-slate-800 flex items-center justify-center text-sm font-bold text-slate-300">E</div>
                <div>
                  <p className="text-sm text-slate-200"><span className="font-semibold text-white">Elachim</span> moved a task to Done</p>
                  <p className="text-xs text-slate-500 mt-1">{i + 1} hour{i === 0 ? '' : 's'} ago</p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}