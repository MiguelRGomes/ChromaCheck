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
import { AddIcon, DeleteIcon, EditIcon } from "@chakra-ui/icons";
import { useHistory } from "react-router-dom";
import AuthApi from "../../../api/auth";

export default function ServiceList() {
  const [services, setServices] = useState([]);
  const history = useHistory();
  const companyId = JSON.parse(localStorage.getItem("userId"));

  useEffect(() => {
    const fetchServices = async () => {
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetService(companyId); // Assumindo que a API retorna os serviços
        const sortedServices = response.data.sort((a, b) => a.name.localeCompare(b.name));
        setServices(sortedServices);
      } catch (err) {
        console.error("Erro ao buscar serviços:", err);
      }
    };

    fetchServices();
  }, [companyId]);

  const gridTemplateColumns = useBreakpointValue({
    base: "repeat(1, 1fr)",
    sm: "repeat(2, 1fr)",
    md: "repeat(3, 1fr)",
    lg: "repeat(4, 1fr)",
  });

  const handleAddServiceClick = () => {
    history.push("/admin/registerservice"); // Navega para a tela de cadastro de serviço
  };

  const handleDeleteService = async (serviceId) => {
    try {
      await AuthApi.DeleteService(serviceId); // Assumindo que este método deleta serviços
      setServices((prevServices) =>
        prevServices.filter((service) => service.id !== serviceId)
      );
    } catch (err) {
      console.error("Erro ao deletar serviço:", err);
    }
  };

  const handleEditService = (serviceId) => {
    history.push(`/admin/editservice/${serviceId}`); // Navega para a tela de edição do serviço
  };

  return (
    <Box p="4" position="relative">
      <Grid
        templateColumns={gridTemplateColumns}
        gap="4"
        autoRows="minmax(100px, auto)"
        mt="15vh"
      >
        {services.map((service) => (
          <GridItem
            key={service.id}
            borderWidth="1px"
            borderRadius="md"
            p="4"
            boxShadow="md"
            bg="white"
            overflow="hidden"
          >
            <Flex direction="column" align="start">
              <Text fontWeight="bold"  mb="1">Código: {service.id}</Text> {/* Exibe o código do serviço */}
              <Text color="gray.500" mb="1">Nome: {service.name}</Text> {/* Exibe o nome do serviço */}
              <Flex justify="flex-end" mt="auto">
                <IconButton
                  icon={<EditIcon />}
                  aria-label="Editar Serviço"
                  size="sm"
                  colorScheme="blue"
                  mr="2"
                  onClick={() => handleEditService(service.id)}
                />
                <IconButton
                  icon={<DeleteIcon />}
                  aria-label="Deletar Serviço"
                  size="sm"
                  colorScheme="red"
                  onClick={() => handleDeleteService(service.id)}
                />
              </Flex>
            </Flex>
          </GridItem>
        ))}
      </Grid>
      <IconButton
        icon={<AddIcon />}
        aria-label="Adicionar Novo Serviço"
        size="lg"
        colorScheme="blue"
        position="fixed"
        bottom="16px"
        right="16px"
        onClick={handleAddServiceClick}
        zIndex="tooltip"
      />
    </Box>
  );
}
