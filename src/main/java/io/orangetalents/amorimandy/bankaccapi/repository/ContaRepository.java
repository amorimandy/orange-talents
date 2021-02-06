package io.orangetalents.amorimandy.bankaccapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.orangetalents.amorimandy.bankaccapi.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	public Optional<Conta> findById(Long id);

	public List<Conta> findByNomeContainingIgnoreCase(String nome);

	public Conta findByEmailContainingIgnoreCase(String email);
}
