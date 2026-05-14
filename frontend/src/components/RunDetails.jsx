
export default function RunDetails({ run }) {
    
    

    return (

        <div>
            <p>Details:</p>
            {run && <p>{run.id}</p>}
            {run && <p>{run.status}</p>}
            {run && <p>{run.createdAt}</p>}
            {run && <p>{run.status}</p>}                                                        
        </div>

    )
}