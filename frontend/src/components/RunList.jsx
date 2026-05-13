
export default function RunList({ runs, setSelectedRun }) {

    return (
        <div className="runs-wrapper">
            {runs.map(
                (run) => 
                    <button
                        className="run-item"                        
                        onClick={()=>setSelectedRun(run.id)}
                     >
                        <p>{run.id} -</p>
                        <p>{run.status}</p>
                    </button>
                )}                      
        </div>   
    )

}