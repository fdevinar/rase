export function formatDate(date) {
    return new Date().toISOString().split('T')[0];
}

export function generateRandomSchedule() {
  const workers = [
    "worker-1",
    "worker-2",
    "worker-3",
    "worker-4",
    "worker-5"
  ];

  const totalShifts = randomBetween(3, 12);

  const shifts = [];

  for (let i = 1; i <= totalShifts; i++) {

    const workersInShift = randomBetween(1, 3);

    const selectedWorkers = shuffle(workers)
      .slice(0, workersInShift);

    shifts.push({
      shiftId: `shift-${i}`,
      workerIds: selectedWorkers
    });
  }

  return {
    scheduleId: `schedule-${Date.now()}`,
    shifts
  };
}

function randomBetween(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function shuffle(array) {
  return [...array].sort(() => Math.random() - 0.5);
}