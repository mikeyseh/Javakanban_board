import { useEffect, useState } from 'react'

const COLUMNS = [
  { key: 'TODO', label: 'To Do', dot: 'bg-blue-500' },
  { key: 'DOING', label: 'Doing', dot: 'bg-amber-500' },
  { key: 'DONE', label: 'Done', dot: 'bg-emerald-500' },
]

export default function Board() {
  const [tasks, setTasks] = useState([])
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState({ title: '', description: '', developer: '', duration: 1 })

  const load = () => fetch('/api/tasks').then(r => r.json()).then(setTasks)
  useEffect(() => { load() }, [])

  const handleDrop = async (e, status) => {
    e.preventDefault()
    const id = e.dataTransfer.getData('taskId')
    if (!id) return
    await fetch(`/api/tasks/${id}/status`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ status })
    })
    load()
  }

  const handleCreate = async (e) => {
    e.preventDefault()
    await fetch('/api/tasks', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ...form, status: 'TODO' })
    })
    setShowForm(false)
    setForm({ title: '', description: '', developer: '', duration: 1 })
    load()
  }

  const handleDelete = async (id) => {
    await fetch(`/api/tasks/${id}`, { method: 'DELETE' })
    load()
  }

  return (
    <div className="flex flex-col h-full overflow-hidden">
      <header className="h-16 flex items-center justify-between px-8 border-b border-slate-800/60 bg-[#0f1115]/80 backdrop-blur-sm z-10 shrink-0">
        <div>
          <h1 className="text-xl font-semibold text-white">Board View</h1>
          <p className="text-xs text-slate-500">Manage your tasks efficiently</p>
        </div>
        <button onClick={() => setShowForm(true)}
          className="bg-indigo-600 hover:bg-indigo-500 text-white px-5 py-2 rounded-lg font-medium text-sm transition-all shadow-lg shadow-indigo-600/20 flex items-center gap-2">
          <svg className="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4v16m8-8H4" /></svg>
          New Task
        </button>
      </header>

      <div className="flex-1 overflow-x-auto overflow-y-hidden p-8">
        <div className="flex gap-6 h-full items-start">
          {COLUMNS.map(col => (
            <div key={col.key}
              onDragOver={(e) => e.preventDefault()}
              onDrop={(e) => handleDrop(e, col.key)}
              className="w-80 flex-shrink-0 flex flex-col h-full max-h-full rounded-2xl bg-[#13151a] border border-slate-800/50 shadow-sm"
            >
              <div className="p-4 flex items-center justify-between border-b border-slate-800/50">
                <div className="flex items-center gap-2">
                  <div className={`w-2 h-2 rounded-full ${col.dot}`} />
                  <h2 className="font-semibold text-sm text-slate-200">{col.label}</h2>
                </div>
                <span className="text-xs font-medium bg-slate-800 text-slate-400 px-2.5 py-0.5 rounded-full">
                  {tasks.filter(t => t.status === col.key).length}
                </span>
              </div>
              <div className="flex-1 overflow-y-auto p-3 space-y-3">
                {tasks.filter(t => t.status === col.key).map(task => (
                  <div key={task.id} draggable
                    onDragStart={(e) => e.dataTransfer.setData('taskId', task.id)}
                    className="bg-[#1c1f26] p-4 rounded-xl border border-slate-700/50 hover:border-slate-500/50 cursor-grab active:cursor-grabbing transition-all group relative overflow-hidden shadow-md"
                  >
                    <div className="flex justify-between items-start mb-2">
                      <h3 className="font-medium text-sm text-slate-100 leading-snug">{task.title}</h3>
                      <button onClick={() => handleDelete(task.id)}
                        className="text-slate-500 hover:text-red-400 opacity-0 group-hover:opacity-100 transition-opacity">
                        <svg className="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" /></svg>
                      </button>
                    </div>
                    <p className="text-xs text-slate-400 mb-4 line-clamp-2">{task.description}</p>
                    <div className="flex items-center justify-between mt-auto pt-3 border-t border-slate-700/30">
                      <div className="flex items-center gap-1.5 text-xs text-slate-400">
                        <div className="w-5 h-5 rounded-full bg-slate-700 flex items-center justify-center text-[10px] font-bold text-slate-300">
                          {task.developer.charAt(0).toUpperCase()}
                        </div>
                        <span className="truncate max-w-[100px]">{task.developer}</span>
                      </div>
                      <div className="flex items-center gap-1 text-xs font-medium bg-slate-800/80 px-2 py-1 rounded-md text-slate-300">
                        <svg className="w-3 h-3" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" /></svg>
                        {task.duration}h
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          ))}
        </div>
      </div>

      {showForm && (
        <div className="fixed inset-0 bg-black/60 backdrop-blur-sm flex items-center justify-center p-4 z-50">
          <form onSubmit={handleCreate} className="bg-[#1c1f26] border border-slate-700/50 rounded-2xl p-6 w-full max-w-md shadow-2xl space-y-4">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-xl font-bold text-white">Create New Task</h2>
              <button type="button" onClick={() => setShowForm(false)} className="text-slate-400 hover:text-white text-xl">&times;</button>
            </div>
            <div>
              <label className="block text-xs font-medium text-slate-400 mb-1">Task Title</label>
              <input required placeholder="e.g. Design landing page" value={form.title}
                onChange={e => setForm({ ...form, title: e.target.value })}
                className="w-full bg-[#0f1115] border border-slate-700 text-sm text-white px-4 py-2.5 rounded-lg focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500" />
            </div>
            <div>
              <label className="block text-xs font-medium text-slate-400 mb-1">Description</label>
              <textarea required placeholder="Task details..." value={form.description}
                onChange={e => setForm({ ...form, description: e.target.value })}
                className="w-full bg-[#0f1115] border border-slate-700 text-sm text-white px-4 py-2.5 rounded-lg focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500" rows="3" />
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-xs font-medium text-slate-400 mb-1">Developer</label>
                <input required placeholder="Name" value={form.developer}
                  onChange={e => setForm({ ...form, developer: e.target.value })}
                  className="w-full bg-[#0f1115] border border-slate-700 text-sm text-white px-4 py-2.5 rounded-lg focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500" />
              </div>
              <div>
                <label className="block text-xs font-medium text-slate-400 mb-1">Duration (hrs)</label>
                <input required type="number" min="1" placeholder="e.g. 5" value={form.duration}
                  onChange={e => setForm({ ...form, duration: parseInt(e.target.value) })}
                  className="w-full bg-[#0f1115] border border-slate-700 text-sm text-white px-4 py-2.5 rounded-lg focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500" />
              </div>
            </div>
            <div className="flex gap-3 pt-2">
              <button type="button" onClick={() => setShowForm(false)}
                className="flex-1 bg-slate-800 hover:bg-slate-700 py-2.5 rounded-lg text-sm font-medium transition-colors">Cancel</button>
              <button type="submit"
                className="flex-1 bg-indigo-600 hover:bg-indigo-500 py-2.5 rounded-lg text-sm font-medium transition-colors shadow-lg shadow-indigo-600/20">Create Task</button>
            </div>
          </form>
        </div>
      )}
    </div>
  )
}