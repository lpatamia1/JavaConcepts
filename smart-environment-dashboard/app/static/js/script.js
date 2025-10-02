fetch('/api/data')
    .then(response => response.json())
    .then(data => {
        const cities = Object.keys(data.air_quality);
        const airValues = Object.values(data.air_quality);
        const waterValues = Object.values(data.water_usage);

        var trace1 = {
            x: cities,
            y: airValues,
            name: 'Air Quality',
            type: 'bar'
        };

        var trace2 = {
            x: cities,
            y: waterValues,
            name: 'Water Usage',
            type: 'bar'
        };

        var chartData = [trace1, trace2];

        Plotly.newPlot('charts', chartData, {barmode: 'group'});
    });
