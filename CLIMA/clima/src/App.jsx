import { useState } from 'react'
import './App.css'
import Clima from './components/FormularioClima'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <h1>CLIMA ZONAL</h1>
        <Clima/>  
      </div>
      
    </>
  )
}

export default App
