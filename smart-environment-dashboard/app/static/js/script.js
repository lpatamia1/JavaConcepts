const city = "Chicago"; // Could make dynamic with user input
fetch(`/api/data/${city}`)
    .then(response => response.json())
    .then(data => {
        const airValues = Object.values(data.air_quality);
        const airLabels = Object.keys(data.air_quality);

        const trace = {
            x: airLabels,
            y: airValues,
            type: 'bar',
            name: 'Air Quality'
        };

        Plotly.newPlot('charts', [trace], {title: `Air Quality in ${city}`});
    });
