export function formatDate(date) {
    return new Date().toISOString().split('T')[0];
}