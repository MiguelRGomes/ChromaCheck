package br.com.java.tcc.application.company;

import br.com.java.tcc.application.company.resources.CompanyRequest;
import br.com.java.tcc.application.company.resources.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse findById(Long id);

    List<CompanyResponse> findAll();

    CompanyResponse register(CompanyRequest companyDTO);

    CompanyResponse update(Long id, CompanyRequest companyDTO);

    String delete(Long id);

}