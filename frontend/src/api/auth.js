import axios from "./index";

class AuthApi {
  //End-points Company
  static Login = (cnpj, password) => {
    return axios.get(`company/login`, {
      params: {
        cnpj,
        password
      }
    });
  };

  static Register = (data) => {
    return axios.post(`company`, data);
  };

  static Logout = (data) => {
    return axios.post(`company`, data, { headers: { Authorization: `${data.token}` } });
  };
  //
  //End-points People
  static RegisterPeople = (data) => {
    return axios.post(`people`, data);
  };

  static GetPeople = (companyId) => {
    return axios.get(`people`, {
      params: {
        companyId
      }
    });
  };

  static GetPeopleId = (id) => {
    return axios.get(`people/${id}`);
  };

  static UpdatePeople = (id, data) => {
    return axios.put(`people/${id}`, data);
  };

  static DeletePeople = (id) => {
    return axios.delete(`people/${id}`);
  };
  //
  //End-points Address
  static RegisterAddress = (data) => {
    return axios.post(`adress`, data);
  };

  static GetAddresses = (personId) => {
    return axios.get(`adress`, {
      params: {
        personId
      }
    });
  };

  static DeleteAddress = (id) => {
    return axios.delete(`adress/${id}`);
  };
  //
  //End-points Service
  static RegisterService = (data) => {
    return axios.post(`services`, data);
  };

  static GetService = (companyId) => {
    return axios.get(`services`, {
      params: {
        companyId
      }
    });
  };

  static DeleteService = (id) => {
    return axios.delete(`services/${id}`);
  };
  //
  //End-points Product
  static RegisterProduct = (data) => {
    return axios.post(`product`, data);
  };

  static GetProduct = (companyId) => {
    return axios.get(`product`, {
      params: {
        companyId
      }
    });
  };

  static DeleteProduct = (id) => {
    return axios.delete(`product/${id}`);
  };
  //
  //End-points Prices
  static RegisterPrice = (data) => {
    return axios.post(`prices`, data);
  };

  static GetPrice = (companyId) => {
    return axios.get(`prices`, {
      params: {
        companyId
      }
    });
  };

  static DeletePrice = (id) => {
    return axios.delete(`prices/${id}`);
  };
  //
  //End-points Budget
  static RegisterBudget = (data) => {
    return axios.post(`budget`, data);
  };

  static GetBudgets = (companyId) => {
    return axios.get(`budget`, {
      params: {
        companyId
      }
    });
  };

  static GetBudgetId = (id) => {
    return axios.get(`budget/${id}`);
  };

  static UpdateBudget = (id, data) => {
    return axios.put(`budget/${id}`, data);
  };

  static DeleteBudget = (id) => {
    return axios.delete(`budget/${id}`);
  };
  //
  //End-points Budget-product
  static RegisterProductBudget = (data) => {
    return axios.post(`budget-products`, data);
  };

  static GetBudgetProducts = (budgetId) => {
    return axios.get(`budget-products`, {
      params: {
        budgetId
      }
    });
  };
  //
  //End-points Budget-services
  static RegisterServiceBudget = (data) => {
    return axios.post(`budget-services`, data);
  };

  static GetBudgetServices = (budgetId) => {
    return axios.get(`budget-services`, {
      params: {
        budgetId
      }
    });
  };
}

//let base = "company";

export default AuthApi;
