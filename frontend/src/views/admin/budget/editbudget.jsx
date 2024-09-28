import React, { useState, useEffect } from "react";
import {
  Box,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Button,
  Text,
  Grid,
  GridItem,
  IconButton,
  useColorModeValue,
} from "@chakra-ui/react";
import { useParams, useHistory } from "react-router-dom";
import AuthApi from "../../../api/auth";
import { DeleteIcon } from "@chakra-ui/icons";

export default function EditBudgetForm() {
  const { id } = useParams();
  const history = useHistory();

  const [budgetData, setBudgetData] = useState({
    budgetCode: "",
    clientCode: "",
    clientName: "",
    address: "",
    addressNumber: "",
    creationDate: "",
    expirationDate: "",
    total: "",
  });

  const [budgetServices, setBudgetServices] = useState([]);
  const [budgetProducts, setBudgetProducts] = useState([]);

  const textColor = useColorModeValue("navy.700", "white");
  const bgColor = useColorModeValue("white", "gray.700");

  useEffect(() => {
    const fetchBudgetData = async () => {
      try {
        // Fetching budget details
        const response = await AuthApi.GetBudgetId(id);
        const data = response.data;

        const creationDate = new Date(data.creation_date).toISOString().split('T')[0];
        const expirationDate = new Date(data.expiration_date).toISOString().split('T')[0];

        setBudgetData({
          budgetCode: data.id,
          clientCode: data.personEntity.id,
          clientName: data.personEntity.name,
          address: data.adressEntity.adress,
          addressNumber: data.adressEntity.number,
          creationDate,
          expirationDate,
          total: data.total,
        });

        // Fetching budget services
        const servicesResponse = await AuthApi.GetBudgetServices(id);
        setBudgetServices(servicesResponse.data || []);

        // Fetching budget products
        const productsResponse = await AuthApi.GetBudgetProducts(id);
        setBudgetProducts(productsResponse.data || []);
      } catch (err) {
        console.error("Erro ao buscar os dados do orçamento:", err);
      }
    };

    fetchBudgetData();
  }, [id]);

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
      await AuthApi.UpdateBudget(id, {
        ...budgetData,
        creation_date: budgetData.creationDate,
        expiration_date: budgetData.expirationDate,
      });
      history.push("/admin/budget");
    } catch (err) {
      console.error("Erro ao atualizar o orçamento:", err);
    }
  };

  const handleClose = () => {
    history.push("/admin/budget");
  };

  const handleDeleteService = async (serviceId) => {
    try {
      await AuthApi.DeleteService(serviceId); // Implement this API call
      setBudgetServices((prevServices) => prevServices.filter(service => service.id !== serviceId));
    } catch (err) {
      console.error("Erro ao deletar o serviço:", err);
    }
  };

  const handleDeleteProduct = async (productId) => {
    try {
      await AuthApi.DeleteProduct(productId); // Implement this API call
      setBudgetProducts((prevProducts) => prevProducts.filter(product => product.id !== productId));
    } catch (err) {
      console.error("Erro ao deletar o produto:", err);
    }
  };

  const handleItemsClick = () => {
    history.push(`/admin/${id}/budget-item`);
  };

  const handleServicesClick = () => {
    history.push(`/admin/${id}/budget-service`);
  };

  const calculateTotal = (service) => {
    const fixedPrice = service.pricesEntity?.fixed_price || 0; // Preço fixo
    const quantity = service.quantity || 0; // Quantidade
    const discount = service.discount || 0; // Desconto
    
    const perc = discount / 100
    const subtotal = (fixedPrice * quantity) * perc; // Cálculo do total
    const total = (fixedPrice * quantity) - subtotal
    return total.toFixed(2); // Retorna o total formatado com duas casas decimais
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
          w={{ base: "100%", lg: "60%" }}
          bg={bgColor}
          p="24px"
          borderRadius="15px"
          boxShadow="lg"
          mr={{ lg: "20px" }}
        >
          <Flex direction="column" w="100%">
            <FormControl>
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Código do Orçamento
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                value={budgetData.budgetCode}
                isReadOnly
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Código do Cliente
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="clientCode"
                value={budgetData.clientCode}
                isReadOnly
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Nome do Cliente
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="clientName"
                value={budgetData.clientName}
                isReadOnly
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Endereço
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="address"
                value={budgetData.address}
                isReadOnly
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Número
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="addressNumber"
                value={budgetData.addressNumber}
                isReadOnly
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Data de Criação
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                type="date"
                name="creationDate"
                value={budgetData.creationDate}
                onChange={handleInputChange}
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Data de Expiração
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                type="date"
                name="expirationDate"
                value={budgetData.expirationDate}
                onChange={handleInputChange}
              />

              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Total
              </FormLabel>
              <Input
                variant="auth"
                fontSize="sm"
                name="total"
                value={budgetData.total}
                onChange={handleInputChange}
              />

              <Flex direction="column" gap="4" mt="4">
                <Flex justifyContent="space-between">
                  <Button
                    fontSize="sm"
                    variant="outline"
                    fontWeight="500"
                    w="48%"
                    onClick={handleItemsClick}
                  >
                    Itens
                  </Button>
                  <Button
                    fontSize="sm"
                    variant="outline"
                    fontWeight="500"
                    w="48%"
                    onClick={handleServicesClick}
                  >
                    Serviços
                  </Button>
                </Flex>
                <Flex justifyContent="space-between">
                  <Button
                    fontSize="sm"
                    variant="outline"
                    fontWeight="500"
                    w="48%"
                    onClick={handleClose}
                  >
                    Fechar
                  </Button>
                  <Button
                    fontSize="sm"
                    variant="brand"
                    fontWeight="500"
                    w="48%"
                    onClick={handleSubmit}
                  >
                    Salvar
                  </Button>
                </Flex>
              </Flex>
            </FormControl>
          </Flex>
        </Box>

        {/* Seção da Grid de Itens */}
<Box
  w={{ base: "100%", lg: "40%" }} // Largura da grid em telas grandes
  bg={bgColor}
  p="24px"
  borderRadius="15px"
  boxShadow="lg"
  overflow="auto"
>
  <Text fontSize="md" fontWeight="bold" mb="2">Serviços e Produtos  </Text>
  <Grid
    templateColumns={{ base: "repeat(1, 1fr)", md: "repeat(2, 1fr)" }}
    gap="4"
    autoRows="minmax(100px, auto)"
  >
    {/* Renderiza Serviços */}
    {budgetServices.length > 0 && (
      <>
        {budgetServices.map((service) => (
          <GridItem key={service.id} borderWidth="1px" borderRadius="md" p="4" boxShadow="md" bg="blue.100" borderColor="blue.500" overflow="hidden">
            <Flex direction="column" align="start">
              <Text fontWeight="bold" mb="1">
                <Text as="span" fontWeight="bold">Serviço: {service.servicesEntity.name}</Text>
              </Text>
              <Text fontSize="sm" color="gray.600" mb="1">Quantidade: {service.quantity}</Text>
              <Text fontSize="sm" color="gray.600" mb="1">Total:  {calculateTotal(service)}</Text>
              <Flex justify="flex-end" mt="auto">
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
        {/* Se a quantidade de serviços for ímpar, adiciona uma célula em branco para iniciar os produtos na linha de baixo */}
        {/*budgetServices.length % 2 !== 0 && <GridItem colSpan={1} />*/}
      </>
    )}
    
    {/* Renderiza Produtos */}
    {budgetProducts.length > 0 && (
      <>
        {budgetProducts.map((item) => (
          <GridItem key={item.id} borderWidth="1px" borderRadius="md" p="4" boxShadow="md" bg="purple.100" borderColor="purple.500" overflow="hidden">
            <Flex direction="column" align="start">
              <Text fontWeight="bold" mb="1">
                <Text as="span" fontWeight="bold">Produto: {item.productEntity.id}</Text> - 
                <Text as="span" fontWeight="bold"> {item.productEntity.name}</Text>
              </Text>
              <Text fontSize="sm" color="gray.600" mb="1">Quantidade: {item.quantity}</Text>
              <Text fontSize="sm" color="gray.600" mb="1">Total: {item.total}</Text>
              <Flex justify="flex-end" mt="auto">
                <IconButton
                  icon={<DeleteIcon />}
                  aria-label="Deletar Item"
                  size="sm"
                  colorScheme="red"
                  onClick={() => handleDeleteProduct(item.id)}
                />
              </Flex>
            </Flex>
          </GridItem>
        ))}
      </>
    )}
  </Grid>
    
      {/* Legenda */}
      <Flex mt="4" justifyContent="center">
        <Flex align="center" mr="8">
          <Box w="16px" h="16px" bg="blue.100" border="2px solid" borderColor="blue.500" mr="2" />
          <Text fontSize="sm">Serviços</Text>
        </Flex>
        <Flex align="center">
          <Box w="16px" h="16px" bg="purple.100" border="2px solid" borderColor="purple.500" mr="2" />
          <Text fontSize="sm">Produtos</Text>
        </Flex>
      </Flex>
    </Box>

      </Flex>
    </Box>
  );
}
