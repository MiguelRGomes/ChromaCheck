import React, { useState, useEffect } from "react";
import {
  Box,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Button,
  Text,
  useColorModeValue,
  IconButton,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalCloseButton,
  ModalBody,
} from "@chakra-ui/react";
import { AddIcon } from "@chakra-ui/icons";
import { useHistory } from "react-router-dom";
import AuthApi from "../../../api/auth";

export default function RegisterBudgetForm() {
  const history = useHistory();

  const [budgetData, setBudgetData] = useState({
    clientCode: "",
    clientName: "",
    address: "",
    addressNumber: "",
    creationDate: "",
    expirationDate: "",
    total: "",
  });

  const [clients, setClients] = useState([]);
  const [addresses, setAddresses] = useState([]);
  const [isClientModalOpen, setIsClientModalOpen] = useState(false);
  const [isAddressModalOpen, setIsAddressModalOpen] = useState(false);

  const textColor = useColorModeValue("navy.700", "white");
  const bgColor = useColorModeValue("white", "gray.700");
  const companyId = JSON.parse(localStorage.getItem("userId"));

  // Fetch clients from API
  useEffect(() => {
    const fetchClients = async () => {
      try {
        const clientResponse = await AuthApi.GetPeople(companyId);
        // Ordena os clientes pelo ID antes de definir o estado
        const sortedClients = clientResponse.data.sort((a, b) => a.id - b.id);
        setClients(sortedClients);
      } catch (err) {
        console.error("Erro ao buscar clientes:", err);
      }
    };
    fetchClients();
  }, [companyId]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setBudgetData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = {
          companyEntity: {
              id: companyId,
          },
          personEntity: {
              id: budgetData.clientCode,
          },
          adressEntity: {
              id: budgetData.addressId, 
          },
          creation_date: budgetData.creationDate,
          expiration_date: budgetData.expirationDate,
          approval: budgetData.approval || false,
          total: budgetData.total,
      };
      
      // Aguarda a resposta da API ao criar o orçamento
      const response = await AuthApi.RegisterBudget(payload);
      
      // Captura o ID do orçamento da resposta
      const budgetId = response.data.id; // Ajuste conforme a estrutura da resposta
      
      // Redireciona para a nova rota com o ID do orçamento
      history.push(`/admin/editbudget/${budgetId}`);
      } catch (err) {
          console.error("Erro ao criar o orçamento:", err);
      }
  };

  const handleClose = () => {
    history.push("/admin/budget");
  };

  const handleClientSelect = async (selectedClient) => {
    setBudgetData((prevData) => ({
      ...prevData,
      clientCode: selectedClient.id,
      clientName: selectedClient.name,
    }));
    setIsClientModalOpen(false);

    // Fetch addresses for the selected client
    try {
      const addressResponse = await AuthApi.GetAddresses(selectedClient.id);
      setAddresses(addressResponse.data);
      setIsAddressModalOpen(true); // Abre o modal de endereços após carregar os endereços
    } catch (err) {
      console.error("Erro ao buscar endereços:", err);
    }
  };

  const handleAddressSelect = (selectedAddress) => {
    setBudgetData((prevData) => ({
      ...prevData,
      addressId: selectedAddress.id,
      address: selectedAddress.adress,
      addressNumber: selectedAddress.number,
    }));
    setIsAddressModalOpen(false);
  };

  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
      <Flex
        direction={{ base: "column", lg: "row" }}
        maxW="100%"
        w="100%"
        mx="0"
        h="100%"
        alignItems="flex-start"
        justifyContent="flex-start"
        mb={{ base: "30px", md: "60px" }}
        px={{ base: "25px", md: "0px" }}
        mt={{ base: "15px", md: "5vh" }}
      >
        <Box
          w={{ base: "100%", lg: "50%" }}
          bg={bgColor}
          p="24px"
          borderRadius="15px"
          boxShadow="lg"
          mr={{ lg: "20px" }}
        >
          <Flex direction="column" w="100%">
            <FormControl>
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Código do Cliente
              </FormLabel>
              <Flex mb="24px">
                <Input
                  variant="auth"
                  fontSize="sm"
                  name="clientCode"
                  placeholder="Digite o código do cliente"
                  value={budgetData.clientCode}
                  isReadOnly
                />
                <IconButton
                  icon={<AddIcon />}
                  aria-label="Selecionar Cliente"
                  onClick={() => setIsClientModalOpen(true)}
                  ml={2}
                />
              </Flex>

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Nome do Cliente
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="clientName"
                placeholder="Digite o nome do cliente"
                value={budgetData.clientName}
                isReadOnly
                mb="24px"
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Endereço
              </FormLabel>
              <Flex mb="24px">
                <Input
                  variant="auth"
                  fontSize="sm"
                  name="address"
                  placeholder="Digite o endereço"
                  value={budgetData.address}
                  isReadOnly
                />
                <IconButton
                  icon={<AddIcon />}
                  aria-label="Selecionar Endereço"
                  onClick={() => setIsAddressModalOpen(true)}
                  ml={2}
                  isDisabled={!budgetData.clientCode} // Desabilita o botão se nenhum cliente estiver selecionado
                />
              </Flex>

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Número
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="addressNumber"
                placeholder="Digite o número"
                value={budgetData.addressNumber}
                isReadOnly
                mb="24px"
              />

              <Input
                variant="auth"
                fontSize="sm"
                name="addressId"
                placeholder="Digite o número"
                value={budgetData.addressId}
                isReadOnly
                style={{ display: "none" }} // Oculta o campo
                mb="24px"
              />

              {/* Campo de data de criação */}
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Data de Criação
              </FormLabel>
              <Input
                type="date"
                variant="auth"
                fontSize="sm"
                name="creationDate"
                value={budgetData.creationDate}
                onChange={handleInputChange}
                mb="24px"
              />

              {/* Campo de data de expiração */}
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Data de Expiração
              </FormLabel>
              <Input
                type="date"
                variant="auth"
                fontSize="sm"
                name="expirationDate"
                value={budgetData.expirationDate}
                onChange={handleInputChange}
                mb="24px"
              />

              {/* Campo de valor total */}
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Valor Total
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="total"
                placeholder="Digite o valor total"
                value={budgetData.total}
                onChange={handleInputChange}
                mb="24px"
              />

              <Flex justifyContent="space-between">
                <Button
                  fontSize="sm"
                  variant="outline"
                  fontWeight="500"
                  w="48%"
                  h="50"
                  onClick={handleClose}
                >
                  Fechar
                </Button>
                <Button
                  fontSize="sm"
                  variant="brand"
                  fontWeight="500"
                  w="48%"
                  h="50"
                  onClick={handleSubmit}
                >
                  Cadastrar
                </Button>
              </Flex>
            </FormControl>
          </Flex>
        </Box>
      </Flex>

      {/* Modal para selecionar cliente */}
      <Modal isOpen={isClientModalOpen} onClose={() => setIsClientModalOpen(false)} size="lg">
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Selecione um Cliente</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            {clients.length > 0 ? (
              clients.map((client) => (
                <Button
                  key={client.id}
                  onClick={() => handleClientSelect(client)}
                  width="100%"
                  mb={2}
                >
                  {client.id} - {client.name}
                </Button>
              ))
            ) : (
              <Text>Nenhum cliente encontrado.</Text>
            )}
          </ModalBody>
        </ModalContent>
      </Modal>

      {/* Modal para selecionar endereço */}
      <Modal isOpen={isAddressModalOpen} onClose={() => setIsAddressModalOpen(false)} size="lg">
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Selecione um Endereço</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            {addresses.length > 0 ? (
              addresses.map((address) => (
                <Button
                  key={address.id}
                  onClick={() => handleAddressSelect(address)}
                  width="100%"
                  mb={2}
                >
                  {address.adress} - {address.number}
                </Button>
              ))
            ) : (
              <Text>Nenhum endereço encontrado.</Text>
            )}
          </ModalBody>
        </ModalContent>
      </Modal>
    </Box>
  );
}
