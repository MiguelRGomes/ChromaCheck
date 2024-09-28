import React from "react";
import Chart from "react-apexcharts"; // Supondo que esteja usando o ApexCharts

const LineChart = ({ chartData, chartOptions }) => {
  return (
    <Chart
      options={chartOptions}
      series={chartData}
      type="line"
      height={350}
    />
  );
};

export default LineChart;
