// Chakra imports
import {
  Box,
  Button,
  Flex,
  Icon,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
// Custom components
import Card from "components/card/Card.js";
import LineChart from "components/charts/LineChart";
import React from "react";
import { IoCheckmarkCircle, IoAlertCircle, IoCloseCircle } from "react-icons/io5";
import { MdBarChart } from "react-icons/md";
// Assets
import { RiArrowUpSFill } from "react-icons/ri";

export default function BudgetStatus(props) {
  const { ...rest } = props;

  // Chakra Color Mode
  const textColor = useColorModeValue("secondaryGray.900", "white");
  const textColorSecondary = useColorModeValue("secondaryGray.600", "white");
  const iconColor = useColorModeValue("brand.500", "white");
  const bgButton = useColorModeValue("secondaryGray.300", "whiteAlpha.100");
  const bgHover = useColorModeValue(
    { bg: "secondaryGray.400" },
    { bg: "whiteAlpha.50" }
  );
  const bgFocus = useColorModeValue(
    { bg: "secondaryGray.300" },
    { bg: "whiteAlpha.100" }
  );

  // Atualizar categorias para meses
  const lineChartOptionsBudgets = {
    chart: {
      type: "line",
      height: 350,
    },
    stroke: {
      curve: "straight",
      width: 4,
    },
    xaxis: {
      categories: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"], // Meses de 2024
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

  // Exemplo de dados para cada mês
  const lineChartDataBudgets = [
    {
      name: "Aprovados",
      data: [100, 200, 150, 300, 400, 250, 300, 400, 450, 500, 600, 700], // Dados para 2024
    },
    {
      name: "Pendentes",
      data: [50, 75, 100, 125, 200, 150, 200, 100, 150, 200, 250, 300], // Dados para 2024
    },
    {
      name: "Cancelados",
      data: [20, 30, 15, 25, 30, 20, 25, 30, 35, 40, 45, 50], // Dados para 2024
    },
  ];

  return (
    <Flex justify="center" align="center" w={{ base: "100%", md: "210%" }} h={{ base: "auto", md: "50vh" }}>
      <Card
        justifyContent="center"
        align="center"
        direction="column"
        w={{ base: "90%", md: "80%" }} // Ajuste a largura do card conforme necessário
        maxW="800px" // Limitar a largura máxima do card
        mb="0px"
        {...rest}
      >
        <Flex justify="space-between" ps="0px" pe="20px" pt="5px">
          <Flex align="center" w="100%" mb="10px">
            <Text
              me="auto"
              color={textColor}
              fontSize={{ base: "lg", md: "xl" }} // Ajuste o tamanho do texto para telas menores
              fontWeight="700"
              lineHeight="100%"
            >
              Orçamentos - Status
            </Text>
            <Button
              ms="auto"
              align="center"
              justifyContent="center"
              bg={bgButton}
              _hover={bgHover}
              _focus={bgFocus}
              _active={bgFocus}
              w="37px"
              h="37px"
              lineHeight="100%"
              borderRadius="10px"
              {...rest}
            >
              <Icon as={MdBarChart} color={iconColor} w="24px" h="24px" />
            </Button>
          </Flex>
        </Flex>
        <Flex w="100%" flexDirection={{ base: "column", lg: "row" }}>
          <Flex flexDirection="column" me={{ base: "0", lg: "22px" }} mt="28px">
            <Text
              color={textColor}
              fontSize={{ base: "2xl", md: "34px" }} // Ajuste o tamanho do texto
              textAlign="start"
              fontWeight="700"
              lineHeight="100%"
            >
              Aprovados: 1.560
            </Text>
            <Flex align="center" mb="20px">
              <Text
                color={textColorSecondary}
                fontSize="sm"
                fontWeight="500"
                mt="4px"
                me="12px"
              >
                Total Aprovados
              </Text>
              <Flex align="center">
                <Icon as={RiArrowUpSFill} color="green.500" me="2px" mt="2px" />
                <Text color="green.500" fontSize="sm" fontWeight="700">
                  +5.25%
                </Text>
              </Flex>
            </Flex>

            <Flex align="center" mb="10px">
              <Icon as={IoCheckmarkCircle} color="green.500" me="4px" />
              <Text color="green.500" fontSize="md" fontWeight="700">
                Aprovados
              </Text>
            </Flex>

            <Flex align="center" mb="10px">
              <Icon as={IoAlertCircle} color="orange.500" me="4px" />
              <Text color="orange.500" fontSize="md" fontWeight="700">
                Pendentes: 720
              </Text>
            </Flex>

            <Flex align="center">
              <Icon as={IoCloseCircle} color="red.500" me="4px" />
              <Text color="red.500" fontSize="md" fontWeight="700">
                Cancelados: 420
              </Text>
            </Flex>
          </Flex>

          <Box minH="260px" w="100%" mt={{ base: "20px", lg: "auto" }}>
            <LineChart
              chartData={lineChartDataBudgets} // Dados atualizados
              chartOptions={lineChartOptionsBudgets} // Opções de gráfico atualizadas
            />
          </Box>
        </Flex>
      </Card>
    </Flex>
  );
}
