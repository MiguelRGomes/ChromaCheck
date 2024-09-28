// variables/charts.js

const lineChartDataBudgets = [
    {
      name: "Aprovados",
      data: [100, 200, 300, 400, 500], // Exemplo de dados
    },
    {
      name: "Pendentes",
      data: [80, 150, 250, 320, 400], // Exemplo de dados
    },
    {
      name: "Cancelados",
      data: [50, 120, 180, 250, 300], // Exemplo de dados
    },
  ];
  
  const lineChartOptionsBudgets = {
    chart: {
      type: "line",
      height: 350,
    },
    stroke: {
      curve: "smooth",
    },
    xaxis: {
      categories: ["Jan", "Feb", "Mar", "Apr", "May"], // Exemplo de categorias
    },
    yaxis: {
      title: {
        text: "Quantidade",
      },
    },
    colors: ["#28a745", "#ffc107", "#dc3545"], // Cores para aprovados, pendentes e cancelados
    legend: {
      position: "top",
    },
  };
  
  // Export as default if needed
  export { lineChartDataBudgets, lineChartOptionsBudgets };
  