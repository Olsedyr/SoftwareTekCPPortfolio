import React, { useState, useEffect } from 'react';
import axios from "axios";
import './MainSite.css';

const MainSite = () => {
    const [odenseTemp, setOdenseTemp] = useState(null);
    const [cphTemp, setCphTemp] = useState(null);
    const [cphTempDate, setCphTempDate] = useState(null);
    const [odenseTempDate, setOdenseTempDate] = useState(null);
    const [odenseTempAvg, setOdenseTempAvg] = useState(null);
    const [cphTempAvg, setCphTempAvg] = useState(null);
    const [tempDifference, setTempDifference] = useState(null);
    const [tempDifferenceAvg, setTempDifferenceAvg] = useState(null);

    const fetchData = async () => {
        try {
            const responseOdense = await axios.get("http://localhost:8080/api/weather/odense/latest");
            const responseKøbenhavn = await axios.get("http://localhost:8080/api/weather/københavn/latest");
            const responseOdenseAvg = await axios.get("http://localhost:8080/api/weather/odense/average");
            const responseKøbenhavnAvg = await axios.get("http://localhost:8080/api/weather/københavn/average");

            const { temperature_2m: odenseTemperature, time: odenseTemperatureDate } = responseOdense.data;
            const { temperature_2m: cphTemperature, time: cphTemperatureDate } = responseKøbenhavn.data;
            const { temperature_2m_mean: odenseTemperatureAvg } = responseOdenseAvg.data;
            const { temperature_2m_mean: cphTemperatureAvg } = responseKøbenhavnAvg.data;

            setOdenseTemp(odenseTemperature);
            setCphTemp(cphTemperature);
            setCphTempDate(cphTemperatureDate);
            setOdenseTempDate(odenseTemperatureDate);
            setCphTempAvg(cphTemperatureAvg);
            setOdenseTempAvg(odenseTemperatureAvg);

            let tempDiff = Math.abs(cphTemperature - odenseTemperature);
            let tempDiffAvg = Math.abs(cphTemperatureAvg - odenseTemperatureAvg);
            setTempDifference(tempDiff.toFixed(2));
            setTempDifferenceAvg(tempDiffAvg.toFixed(2));
        } catch (error) {
            console.error('Error fetching temperature data:', error);
        }
    };

    useEffect(() => {
        const intervalId = setInterval(() => {
            fetchData();
        }, 900000); // Fetch data every 15 minutes

        fetchData();

        return () => clearInterval(intervalId);
    }, []);

    return (
        <div className="container">
            <div className="header">
                <h1>API Weather Checker</h1>
                <br/>
            </div>
            <div className="dashboard">
                <div className="info-box">
                    <h2>Temperature for Odense and Copenhagen</h2>
                    <div className="box-content">
                        <br/>
                        <p>
                            <strong>OdenseTemp: </strong>
                            {odenseTemp !== null ? `${odenseTemp} °C` : ''}
                            <br/>
                            {odenseTempDate !== null ? `${new Date(odenseTempDate).toLocaleString('en-DK', { hour12: false })} ` : ''}
                        </p>
                        <br/>
                        <p>
                            <strong>CphTemp: </strong>
                            {cphTemp !== null ? `${cphTemp} °C` : ''}
                            <br/>
                            {cphTempDate !== null ? `${new Date(cphTempDate).toLocaleString('en-DK', { hour12: false })} ` : ''}
                        </p>
                    </div>
                </div>
                <div className="info-box">
                    <h2>Average temperature for Odense and Copenhagen (Last 7 days)</h2>
                    <div className="box-content">
                        <br/>
                        <p><strong>OdenseTemp Average: </strong>{odenseTempAvg !== null ? `${odenseTempAvg} °C` : 'Loading...'}</p>
                        <br/>
                        <p><strong>CPHTemp Average: </strong>{cphTempAvg !== null ? `${cphTempAvg} °C` : 'Loading...'}</p>
                    </div>
                </div>
                <div className="info-box">
                    <h2>Temperature Comparison (Odense/CPH)</h2>
                    <div className="box-content">
                        <br/>
                        <p><strong>Current difference: </strong>{tempDifference !== null ? `${tempDifference} °C` : 'Loading...'} </p>
                        <br/>
                        <p><strong>Average difference: </strong>{tempDifferenceAvg !== null ? `${tempDifferenceAvg} °C` : 'Loading...'} </p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MainSite;
