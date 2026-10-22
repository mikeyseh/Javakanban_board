export default function Placeholder({ title }) {
  return (
    <div className="flex flex-col h-full">
      <header className="h-16 flex items-center px-8 border-b border-slate-800/60 bg-[#0f1115]/80 backdrop-blur-sm shrink-0">
        <h1 className="text-xl font-semibold text-white">{title}</h1>
      </header>
      <div className="flex-1 flex items-center justify-center p-8">
        <div className="text-center space-y-4">
          <div className="w-16 h-16 bg-slate-800 rounded-2xl mx-auto flex items-center justify-center text-slate-500">
            <svg className="w-8 h-8" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10" /></svg>
          </div>
          <h2 className="text-2xl font-bold text-white">{title} Module</h2>
          <p className="text-slate-400 max-w-sm mx-auto">This section is currently under construction. Check back soon for updates.</p>
        </div>
      </div>
    </div>
  )
}