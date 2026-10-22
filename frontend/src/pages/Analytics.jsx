export default function Analytics() {
  const bars = [40, 70, 45, 90, 65, 85, 100]
  const days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']

  return (
    <div className="flex flex-col h-full">
      <header className="h-16 flex items-center px-8 border-b border-slate-800/60 bg-[#0f1115]/80 backdrop-blur-sm shrink-0">
        <h1 className="text-xl font-semibold text-white">Analytics</h1>
      </header>
      <div className="flex-1 overflow-y-auto p-8">
        <div className="bg-[#13151a] border border-slate-800/50 rounded-2xl p-8 h-96 flex flex-col justify-end">
          <h2 className="text-lg font-semibold text-white mb-8">Tasks Completed This Week</h2>
          <div className="flex items-end justify-between h-full gap-4">
            {bars.map((h, i) => (
              <div key={i} className="flex-1 flex flex-col items-center gap-2 group">
                <div className="w-full bg-indigo-500/20 group-hover:bg-indigo-500/40 rounded-t-lg transition-all relative" style={{ height: `${h}%` }}>
                  <div className="absolute bottom-0 w-full bg-indigo-500 rounded-t-lg transition-all" style={{ height: '40%' }}></div>
                </div>
                <span className="text-xs text-slate-500 font-medium">{days[i]}</span>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}