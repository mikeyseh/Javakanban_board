export default function Projects() {
  const projects = [
    { name: 'Website Redesign', progress: 75, color: 'bg-indigo-500', members: ['E', 'J', 'M'] },
    { name: 'Mobile App v2', progress: 45, color: 'bg-emerald-500', members: ['S', 'K'] },
    { name: 'Q4 Marketing', progress: 90, color: 'bg-amber-500', members: ['E', 'A', 'L', 'T'] },
  ]

  return (
    <div className="flex flex-col h-full">
      <header className="h-16 flex items-center px-8 border-b border-slate-800/60 bg-[#0f1115]/80 backdrop-blur-sm shrink-0">
        <h1 className="text-xl font-semibold text-white">Projects</h1>
      </header>
      <div className="flex-1 overflow-y-auto p-8">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {projects.map((proj, i) => (
            <div key={i} className="bg-[#13151a] border border-slate-800/50 p-6 rounded-2xl hover:border-slate-600 transition-colors cursor-pointer">
              <h2 className="text-lg font-semibold text-white mb-2">{proj.name}</h2>
              <div className="flex justify-between text-xs text-slate-400 mb-2 mt-6">
                <span>Progress</span>
                <span className="font-medium text-slate-300">{proj.progress}%</span>
              </div>
              <div className="w-full bg-slate-800 rounded-full h-2 mb-6">
                <div className={`h-2 rounded-full ${proj.color}`} style={{ width: `${proj.progress}%` }}></div>
              </div>
              <div className="flex -space-x-2">
                {proj.members.map((m, j) => (
                  <div key={j} className="w-8 h-8 rounded-full bg-slate-700 border-2 border-[#13151a] flex items-center justify-center text-xs font-bold text-slate-300">{m}</div>
                ))}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}