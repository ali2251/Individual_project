import React from 'react';
import axios from 'axios';
import https from 'https';
import {LineChart} from 'react-easy-chart';

export default class HelloWorldDashboard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
        this.constructData = this.constructData.bind(this);
    }
 componentWillMount() {
     console.log("componentWillMountme")

           this.setState({
            latencies: [1, 2, 3],
            throughput: [1, 2, 3],
            jitter: [1, 2, 3],
            bandwidth: [1, 2, 3],
            packetloss: [1, 2, 3],
            links: []
        });

        axios.get('https://192.168.133.23:3000/user/getAllLinks').then((res) => {
            console.log('hey', res)
            this.setState({links: res.data.Links});
            return new Promise((resolve, reject) => {
                if (this.state.links !== undefined) {
                    resolve(true);
                } else {
                    reject(false);
                }
            })
        }).then((result) => {
            if (result) {
                this.constructData('latency');
                this.constructData('jitter');
                this.constructData('throughput');
                this.constructData('bandwidth');
                this.constructData('packetloss');
            }
        }).catch((error) => {
            console.error(error);
        });

 }

 
    constructData(typeOfData) {

        console.log('type of data: ', typeOfData);
        let latencyDataPoints = [];

        let links = this.state.links;
        console.log(links.length, '  -------------------')
        for (let i = 0; i < links.length; ++i) {

            console.log('looping', i);

            let linkId = links[i].id;
            console.log('links', links[i].id);
            var latencies = links[i].latency;//[20,40,60,80,10];
            if (typeOfData === 'latency') {
                latencies = links[i].latency;
            } else if (typeOfData === 'throughput') {
                latencies = links[i].throughput;
            } else if (typeOfData === 'jitter') {
                latencies = links[i].jitter;
            } else if (typeOfData === 'packetloss') {
                latencies = links[i].packetloss;
            } else if (typeOfData === 'bandwidth') {
                latencies = links[i].bandwidth;
            }
            var dates = links[i].date;//[new Date(), new Date(), new Date(), new Date(), new Date()];
            var formattedDates = [];
            var dataPoints = [];

            for (var j = 0; j < dates.length; ++j) {

                var d = new Date(dates[j]);

                var fullYear = d.getFullYear();
                var month = d.getMonth() + 1;
                var date1 = d.getDate();

                var hours = d.getHours();
                var minutes = d.getMinutes();

                var newDate = date1 + '-' + month + '-' + fullYear + ' ' + hours + ':' + minutes;

                formattedDates.push(newDate);
            }

            for (var k = 0; k < latencies.length; ++k) {
                dataPoints.push({x: formattedDates[k], y: latencies[k]});
            }


            console.log(dataPoints);

            var manualData = [
                {x: '5-3-2018 17:00', y: 200000},
                {x: '5-3-2018 17:30', y: 100000},
                {x: '5-3-2018 18:00', y: 330000},
                {x: '5-3-2018 18:30', y: 450000},
                {x: '5-3-2018 19:00', y: 150000}
            ];

            //console.log(manualData)
            console.log('test 1');

            //return dataPoints;
            latencyDataPoints.push(dataPoints);

        

        }

        if (typeOfData === 'latency') {
            this.setState({latencies: latencyDataPoints});
        } else if (typeOfData === 'throughput') {
            console.log('throughput: ');
            this.setState({throughput: latencyDataPoints});
        } else if (typeOfData === 'jitter') {
            this.setState({jitter: latencyDataPoints});
        } else if (typeOfData === 'packetloss') {
            this.setState({packetloss: latencyDataPoints});
        } else if (typeOfData === 'bandwidth') {
            this.setState({bandwidth: latencyDataPoints});
        }

   
    }


    render() {
        let html;
        html = (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">Welcome to React</h1>
                </header>
                <p className="App-intro">
                    To get started, edit <code>src/App.js</code> and save to reload.
                </p>


                <LineChart
                    axisLabels={{x: 'Hour', y: 'Percentage'}}
                    datePattern={'%d-%m-%Y %H:%M'}
                    xType={'time'}
                    verticalGrid
                    lineColors={['pink', 'cyan', 'black', 'cyan']}
                    grid
                    margin={{top: 10, right: 0, bottom: 30, left: 100}}
                    axes
                    interpolate={'cardinal'}
                    width={750}
                    height={250}
                    data={this.state.latencies}

                />

                <LineChart
                    axisLabels={{x: 'Hour', y: 'Percentage'}}
                    datePattern={'%d-%m-%Y %H:%M'}
                    xType={'time'}
                    verticalGrid
                    lineColors={['pink', 'cyan', 'black', 'cyan']}
                    grid
                    margin={{top: 10, right: 0, bottom: 30, left: 100}}
                    axes
                    interpolate={'cardinal'}
                    width={750}
                    height={250}
                    data={this.state.jitter}

                />
                <LineChart
                    axisLabels={{x: 'Hour', y: 'Percentage'}}
                    datePattern={'%d-%m-%Y %H:%M'}
                    xType={'time'}
                    verticalGrid
                    lineColors={['pink', 'cyan', 'black', 'cyan']}
                    grid
                    margin={{top: 10, right: 0, bottom: 30, left: 100}}
                    axes
                    interpolate={'cardinal'}
                    width={750}
                    height={250}
                    data={this.state.packetloss}

                />

                <LineChart
                    axisLabels={{x: 'Hour', y: 'Percentage'}}
                    datePattern={'%d-%m-%Y %H:%M'}
                    xType={'time'}
                    verticalGrid
                    lineColors={['pink', 'cyan', 'black', 'cyan']}
                    grid
                    margin={{top: 10, right: 0, bottom: 30, left: 100}}
                    axes
                    interpolate={'cardinal'}
                    width={750}
                    height={250}
                    data={this.state.throughput}

                />
                <LineChart
                    axisLabels={{x: 'Hour', y: 'Percentage'}}
                    datePattern={'%d-%m-%Y %H:%M'}
                    xType={'time'}
                    verticalGrid
                    lineColors={['pink', 'cyan', 'black', 'cyan']}
                    grid
                    margin={{top: 10, right: 0, bottom: 30, left: 100}}
                    axes
                    interpolate={'cardinal'}
                    width={750}
                    height={250}
                    data={this.state.bandwidth}

                />


            </div>
        );
        return html;
    }
}

class HelloWorldView extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        let html;
        html = (
           <div>All Statistics</div>
        );
        return html;
    }
}