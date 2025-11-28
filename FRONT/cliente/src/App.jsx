import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ClienteForm from './components/ClienteForm'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <h1>Formulario Cliente</h1>
        <ClienteForm/>  
      </div>
      
    </>
  )
}

export default App
