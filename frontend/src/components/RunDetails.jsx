import { formatDate } from "../utils/utils";

export default function RunDetails({ run }) {
    
    // console.log(run);
    const report = run?.resultJson;    
    let executionReport;
    let policyResults;
    let workerResults;
    let successRate;
    if (report) {
        const parsedReport = JSON.parse(report);        
        executionReport = parsedReport.executionReport;
        successRate = (executionReport.successfulShifts / executionReport.totalShifts) * 100 ;
        policyResults = parsedReport.policyResults;
        workerResults = parsedReport.workerResults;
        console.log("** Execution Report **");
        console.log(executionReport);
        console.log("** Success Rate **");
        console.log(successRate);
        console.log("** Policy Results **");
        console.log(policyResults);
        console.log("** Worker Results **");
        console.log(workerResults);
    }
        
    return (

        <div className="details-wrapper">   

            <h2>Run Details</h2>

            {/* BASICS */}

            <div className="basics-wrapper">
                <div className="header-row">
                    <div className="header-item">ID</div>
                    <div className="header-item">Status</div>
                    <div className="header-item">Created At</div>
                </div>

                {run && (
                <>
                    <div className="detail-item">
                    <p>{run.id}</p>
                    <p>{run.status}</p>
                    <p>{formatDate(run.createdAt)}</p>
                    </div>

                </>
                )}     
            </div>

            {/* EXECUTION REPORT */}
            {executionReport && (
            <>
                <h2>Shift Execution Overview</h2>
                <div className="execution-report">
                    <div className="execution-item total">
                        <p className="number">{executionReport.totalShifts}</p>
                        <p className="text">Total Shifts</p>
                    </div>
                    <div className="execution-item success">
                        <p className="number">{executionReport.successfulShifts}</p>
                        <p className="text">Successful</p>
                    </div>
                    <div className="execution-item fail">
                        <p className="number">{executionReport.failedShifts}</p>
                        <p className="text">Failed</p>
                    </div>
                    <div className="execution-item percentage">
                        <p className="number">{successRate.toFixed(0)} %</p>
                        <p className="text">Success Rate</p>
                    </div>
                </div>
            </>
            )}

            {/* POLICY RESULTS */}
            {policyResults && (
            <>
                <h2>Policy Results</h2>
                <div className="policy-results">
                    <div className="policy-item">
                        <p>System Execution:</p>
                        <p>{policyResults.systemExecution}</p>
                    </div>
                    <div className="policy-item">
                        <p>User Execution:</p>
                        <p>{policyResults.userExecution}</p>
                    </div>
                    <div className="policy-item">
                        <p>Suggested Action:</p>
                        <p>{policyResults.suggestedAction}</p>
                    </div>
                </div>
            </>
            )}

            {/* WORKER RESULTS */}
            {workerResults && (
            <>
                <h2>Worker Results</h2>
                <div className="worker-wrapper">
                    <div className="header-row">
                        <div className="header-item">ID</div>
                        <div className="header-item">Total Shifts</div>
                        <div className="header-item">Final Fatigue</div>
                        <div className="header-item">is Fatigued?</div>
                    </div>
                    {workerResults.map((worker) =>
                            <div className="worker-item">
                                <p>{worker.workerId}</p>
                                <p>{worker.totalShiftsWorked}</p>
                                <p>{worker.finalFatigue}</p>
                                <p>{worker.isFatigued ? 'Yes' : 'No'}</p>
                            </div>
                    )}
                </div>
            </>
            )}
                                                              
        </div>

    )
}

         

// finalFatigue
// isFatigued
// totalShiftsWorked
// workerId
