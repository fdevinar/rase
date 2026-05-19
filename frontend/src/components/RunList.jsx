
export default function RunList({ runs, setSelectedRun }) {

    function formatDate(date) {
        return new Date().toISOString().split('T')[0];
    }

    return (
        <div className="runs-wrapper">
            <div className="header-row">
                <div className="header-item">ID</div>
                <div className="header-item">Status</div>
                <div className="header-item">Created At</div>
            </div>
            {runs.map(
                (run) =>                     
                    <button
                        className="run-item"                        
                        onClick={()=>setSelectedRun(run.id)}
                     >
                        <p>{run.id}</p>
                        <p>{run.status}</p>
                        <p>{formatDate(run.createdAt)}</p>
                    </button>
                )}                      
        </div>   
    )

}