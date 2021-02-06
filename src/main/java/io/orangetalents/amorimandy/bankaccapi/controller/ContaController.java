package io.orangetalents.amorimandy.bankaccapi.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.orangetalents.amorimandy.bankaccapi.model.Conta;
import io.orangetalents.amorimandy.bankaccapi.repository.ContaRepository;

@RestController
@RequestMapping("/contas")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaRepository pessoarep;

	@GetMapping("/{id}")
	public ResponseEntity<Conta> buscaPorId(@PathVariable long id) {
		return pessoarep.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/usuarios/{nome}")
	public ResponseEntity<List<Conta>> buscaPorNome(@PathVariable String nome) {
		List<Conta> pessoa = pessoarep.findByNomeContainingIgnoreCase(nome);

		if (pessoa.size() > 0) {
			return ResponseEntity.ok(pessoa);
		} else {
			return new ResponseEntity<List<Conta>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/usuario/email/{email}")
	public ResponseEntity<Conta> buscaPorEmail(@PathVariable String email) {

		Conta user = pessoarep.findByEmailContainingIgnoreCase(email);

		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return new ResponseEntity<Conta>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping
	public ResponseEntity<String> cadastro(@RequestBody Conta usuario) {

		try {
			pessoarep.save(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			if (e.getCause() != null) {
				ConstraintViolationException e1 = (ConstraintViolationException) e.getCause();
				SQLIntegrityConstraintViolationException e2 = (SQLIntegrityConstraintViolationException) e1.getCause();
				return new ResponseEntity<String>("CPF ou email já cadastrado(s) " + e2.getMessage(),
						HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PutMapping
	public ResponseEntity<Conta> alteracao(@RequestBody Conta usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoarep.save(usuario));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		pessoarep.deleteById(id);
	}
}
