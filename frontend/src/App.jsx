import { useEffect, useState } from 'react'
import logo from './assets/rase-logo.png'
import './App.css'

function App() {
  const [runs, setRuns] = useState([]);
  const [customReq, setCustomReq] = useState("");
  const jsonRequest = 
  {"scheduleId": "schedule-1","shifts": [{"shiftId": "shift-1","workerIds": ["worker-1", "worker-2"]},{"shiftId": "shift-2","workerIds": ["worker-3"]}]}

  useEffect(() => {
    fetchRuns();
  },[])

  async function fetchRuns() {
    const response = await fetch("http://localhost:8080/runs");
    const data = await response.json();
    console.log(data);
    setRuns(data);
  }

  function handleSubmit(event) {
    event.preventDefault();
    try {
      const parsedRequest = JSON.parse(customReq);
      runSchedule(parsedRequest);
    }
    catch {
      alert("Invalid JSON Request");
    }    
  }

  async function runSchedule(schedule = jsonRequest) {    
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(schedule)
      };
    const response = await fetch('http://localhost:8080/schedule/execute',requestOptions);
    const data = await response.json();
    console.log(data);
    fetchRuns();
  }
  

  return (
    <>
      <main>

        <div className="hero">
          <img src={logo}alt="logo" />          
          <h1>Resource Allocation Simulation Engine</h1>
        </div>

        <form onSubmit={handleSubmit}>
          <label>Send custom request: </label>
          <textarea 
            type="text" 
            value={customReq}
            onChange={(e)=> setCustomReq(e.target.value)}
          />
          <input type="submit" />
        </form>


        <button onClick={() => runSchedule()}>Send default request</button>

        <h2>RUNS</h2>     
        <div className="runs-wrapper">
          {runs.map((run) => (<p>ID: {run.id} - STATUS: {run.status}</p>))}                            
        </div>   

      </main>                  
    </>
  )
}

export default App
