import React, { useState, useEffect } from "react";
import {
  Box,
  Flex,
  Grid,
  GridItem,
  Text,
  useBreakpointValue,
  IconButton,
} from "@chakra-ui/react";
import { DeleteIcon, EditIcon, AddIcon, DownloadIcon } from "@chakra-ui/icons"; // Importar o ícone de download
import AuthApi from "../../../api/auth";
import { useHistory } from "react-router-dom";
import jsPDF from 'jspdf';
import logo from 'assets/img/auth/auth.png'

export default function BudgetList() {
  const [budgets, setBudgets] = useState([]);
  const [error, setError] = useState(undefined);

  const history = useHistory();
  const companyId = JSON.parse(localStorage.getItem("userId"));

  useEffect(() => {
    const fetchBudgets = async () => {
      try {
        const response = await AuthApi.GetBudgets(companyId);
        
        // Ordena os orçamentos pelo ID em ordem crescente
        const sortedBudgets = response.data.sort((a, b) => Number(a.id) - Number(b.id));
        
        setBudgets(sortedBudgets);
      } catch (err) {
        setError("Erro ao buscar os orçamentos.");
        console.error("Erro ao buscar os orçamentos:", err);
      }
    };

    fetchBudgets();
  }, [companyId]);

  const gridTemplateColumns = useBreakpointValue({
    base: "repeat(1, 1fr)",
    sm: "repeat(2, 1fr)",
    md: "repeat(3, 1fr)",
    lg: "repeat(4, 1fr)",
  });

  const handleEditBudget = (budgetId) => {
    history.push(`/admin/editbudget/${budgetId}`);
  };

  const handleDeleteBudget = async (budgetId) => {
    try {
      await AuthApi.DeleteBudget(budgetId);
      setBudgets(budgets.filter((budget) => budget.id !== budgetId));
    } catch (err) {
      setError("Erro ao excluir o orçamento.");
      console.error("Erro ao excluir o orçamento:", err);
    }
  };

  const handleAddBudgetClick = () => {
    history.push("/admin/registerbudget");
  };

 // Função para gerar e baixar o PDF
 const handleDownloadPDF = async (budgetId) => {
  try {
      // Chamada para obter o orçamento pelo ID
      const budgetResponse = await AuthApi.GetBudgetId(budgetId);
      const budget = budgetResponse.data;

      // Chamada para obter os produtos do orçamento
      const productsResponse = await AuthApi.GetBudgetProducts(budgetId);
      const products = productsResponse.data;

      // Chamada para obter os serviços relacionados ao orçamento
      const servicesResponse = await AuthApi.GetBudgetServices(budgetId);
      const services = servicesResponse.data;
      
      const img = new Image();
      img.src = logo;

      // Agora que temos todas as informações, podemos gerar o PDF
      const doc = new jsPDF();

      // Definir margens e espaciamento inicial
      const marginLeft = 20;
      let yPos = 20;

      doc.addImage(img, 'PNG', 150, 10, 50, 20); // X, Y, Largura, Altura

      // Título do PDF
      doc.setFontSize(18);
      doc.setTextColor(0, 51, 102);  // Define a cor para azul escuro
      doc.setFont("helvetica", "bold"); // Negrito para os títulos
      doc.text(`Orçamento ID: ${budget.id}`, marginLeft, yPos);
      yPos += 10;

      // Informações do cliente com subtítulo
      doc.setFontSize(14);
      doc.setTextColor(0, 0, 0);  // Texto preto
      doc.setFont("helvetica", "bold"); // Negrito para os títulos
      yPos += 10;

      doc.setFontSize(12);
      doc.setFont("helvetica", "normal"); // Texto normal
      doc.text(`Cliente: ${budget.personEntity.name}`, marginLeft, yPos);
      yPos += 10;
      doc.text(`Endereço: ${budget.adressEntity.adress}, ${budget.adressEntity.number}`, marginLeft, yPos);
      yPos += 10;
      doc.text(`Data de Criação: ${new Date(budget.creation_date).toLocaleDateString()}`, marginLeft, yPos);
      yPos += 10;
      doc.text(`Data de Expiração: ${new Date(budget.expiration_date).toLocaleDateString()}`, marginLeft, yPos);
      yPos += 10;
      doc.text(`Total: R$ ${budget.total.toFixed(2)}`, marginLeft, yPos);
      yPos += 15;

      // Adicionando uma linha divisória
      doc.setDrawColor(0, 51, 102);  // Cor azul escuro
      doc.line(marginLeft, yPos, 190, yPos);  // Desenha uma linha horizontal
      yPos += 10;

      // Adicionando os serviços ao PDF com subtítulo
      doc.setFontSize(14);
      doc.setFont("helvetica", "bold");
      doc.setTextColor(0, 51, 102);
      doc.text('Serviços:', marginLeft, yPos);
      yPos += 10;

      // Definir tabela de serviços
      doc.setFontSize(12);
      doc.setFont("helvetica", "normal");
      doc.setTextColor(0, 0, 0);

      services.forEach((service, index) => {
        const perc = service.discount / 100
        const totalBrutoService = service.pricesEntity.fixed_price * service.quantity;
        const subtotal = totalBrutoService * perc; // Cálculo do total
        const totalService = totalBrutoService - subtotal
        doc.text(`${index + 1}. ${service.servicesEntity.name} - R$ ${totalService.toFixed(2)}`, marginLeft, yPos);
        yPos += 5;
        doc.text(`Quantidade: ${service.quantity}`, marginLeft, yPos);
        yPos += 10;
      });

      yPos += 10;  // Espaçamento antes da próxima seção

      // Adicionando os produtos ao PDF com subtítulo
      doc.setFontSize(14);
      doc.setFont("helvetica", "bold");
      doc.setTextColor(0, 51, 102);
      doc.text('Produtos:', marginLeft, yPos);
      yPos += 10;

      // Definir tabela de produtos
      doc.setFontSize(12);
      doc.setFont("helvetica", "normal");
      doc.setTextColor(0, 0, 0);

      products.forEach((product, index) => {
        const totalProduct = product.unit_price * product.quantity;

        doc.text(`${index + 1}. ${product.productEntity.name} - R$ ${totalProduct.toFixed(2)}`, marginLeft, yPos);
        yPos += 5;
        doc.text(`Quantidade: ${product.quantity}`, marginLeft, yPos);
        yPos += 10;
      });

      // Adicionar rodapé ao PDF (pode ser informações da empresa, data, etc.)
      doc.setFontSize(10);
      doc.setTextColor(150, 150, 150);
      doc.text(`Orçamento gerado em: ${new Date().toLocaleDateString()}`, marginLeft, 290);

      // Salva o PDF
      doc.save(`Orcamento_${budget.id}.pdf`);
    } catch (err) {
      console.error("Erro ao gerar PDF do orçamento:", err);
    }
  };

  return (
    <Box p="4" position="relative">
      <Text mb="36px" color="gray.400" fontWeight="400" fontSize="md">
        Lista de Orçamentos
      </Text>
      {error && (
        <Text color="red.500" textAlign="center">
          {error}
        </Text>
      )}
      <Grid
        templateColumns={gridTemplateColumns}
        gap={4}
        autoRows="minmax(100px, auto)"
        mt="15vh"
      >
        {budgets.map((budget) => (
          <GridItem
            key={budget.id}
            borderWidth="1px"
            borderRadius="md"
            p="4"
            boxShadow="md"
            bg={budget.approval ? 'green.100' : 'yellow.100'}
            borderColor={budget.approval ? 'green.500' : 'yellow.500'}
            overflow="hidden"
          >
            <Flex direction="column" align="start">
              <Text fontWeight="bold" mb="1">{`Orçamento: ${budget.id}`}</Text>
              <Text fontWeight="bold" mb="1">{`Cliente: ${budget.personEntity.id} - ${budget.personEntity.name}`}</Text>
              <Text fontSize="sm" color="gray.600" mb="1">{`Endereço: ${budget.adressEntity.adress}, ${budget.adressEntity.number}`}</Text>
              <Text fontSize="sm" color="gray.600" mb="3">
                {`Data de Criação: ${new Date(budget.creation_date).toLocaleDateString()}`}
              </Text>
              <Text fontSize="sm" color="gray.600" mb="3">
                {`Data de Expiração: ${new Date(budget.expiration_date).toLocaleDateString()}`}
              </Text>
              <Text fontSize="sm" color="gray.600" mb="3">{`Total: R$ ${budget.total.toFixed(2)}`}</Text>
              <Flex justify="flex-end" mt="auto">
                <IconButton
                  icon={<EditIcon />}
                  aria-label="Editar Orçamento"
                  size="sm"
                  colorScheme="blue"
                  mr="2"
                  onClick={() => handleEditBudget(budget.id)}
                />
                <IconButton
                  icon={<DeleteIcon />}
                  aria-label="Deletar Orçamento"
                  size="sm"
                  colorScheme="red"
                  mr="2"
                  onClick={() => handleDeleteBudget(budget.id)}
                />
                <IconButton
                  icon={<DownloadIcon />} // Ícone de download
                  aria-label="Baixar PDF"
                  size="sm"
                  colorScheme="green"
                  onClick={() => handleDownloadPDF(budget.id)} // Chama a função de download
                />
              </Flex>
            </Flex>
          </GridItem>
        ))}
      </Grid>
      <IconButton
        icon={<AddIcon />}
        aria-label="Adicionar Novo Orçamento"
        size="lg"
        colorScheme="blue"
        position="fixed"
        bottom="16px"
        right="16px"
        onClick={handleAddBudgetClick}
        zIndex="tooltip"
      />
    </Box>
  );
}
