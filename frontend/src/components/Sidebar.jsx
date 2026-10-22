import { NavLink } from 'react-router-dom'

const navItems = [
  { name: 'Overview', path: '/overview' },
  { name: 'Projects', path: '/projects' },
  { name: 'Analytics', path: '/analytics' },
  { name: 'Tasks', path: '/tasks' },
  { name: 'Reports', path: '/reports' },
]

export default function Sidebar() {
  return (
    <aside className="w-64 border-r border-slate-800/60 bg-[#0f1115] flex flex-col hidden md:flex shrink-0">
      <div className="h-16 flex items-center px-6 border-b border-slate-800/60 font-bold text-white text-xl tracking-tight">
        <span className="w-8 h-8 rounded-lg bg-gradient-to-br from-indigo-500 to-purple-600 flex items-center justify-center mr-3 shadow-lg shadow-indigo-500/20">K</span>
        Kanban
      </div>
      <div className="p-4 flex-1">
        <div className="space-y-1">
          {navItems.map((item) => (
            <NavLink
              key={item.name}
              to={item.path}
              className={({ isActive }) =>
                `flex items-center px-4 py-2.5 rounded-lg text-sm font-medium cursor-pointer transition-colors ${
                  isActive ? 'bg-slate-800/80 text-white' : 'hover:bg-slate-800/40 text-slate-400 hover:text-slate-200'
                }`
              }
            >
              {item.name}
            </NavLink>
          ))}
        </div>
      </div>
      <div className="p-4 border-t border-slate-800/60">
        <div className="flex items-center gap-3 px-2">
          <div className="w-9 h-9 rounded-full bg-gradient-to-tr from-purple-500 to-pink-500 flex items-center justify-center text-white font-bold text-sm shadow-lg shadow-pink-500/20">E</div>
          <div>
            <p className="text-sm font-semibold text-white">Elachim</p>
            <p className="text-xs text-slate-500">Workspace</p>
          </div>
        </div>
      </div>
    </aside>
  )
}