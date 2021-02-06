//package io.orangetalents.amorimandy.bankaccapi.controller;
//
////import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.List;
//import java.util.Optional;
////import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import io.orangetalents.amorimandy.bankaccapi.model.Pessoa;
//import io.orangetalents.amorimandy.bankaccapi.model.UserLogin;
//import io.orangetalents.amorimandy.bankaccapi.repository.PessoaRepository;
//import io.orangetalents.amorimandy.bankaccapi.service.PessoaService;
//
//@RestController
//@RequestMapping("/accounts")
//@CrossOrigin(value = "*", allowedHeaders = "*")
//public class PessoaController {
//	
//	@Autowired
//	private PessoaRepository pessoarep;	
//	
//	@Autowired
//	private PessoaService pessoaService;
//	
//	@GetMapping
//	public ResponseEntity<List<Pessoa>> GetAll(){
//		return ResponseEntity.ok(pessoarep.findAll());
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Pessoa> buscaPorId (@PathVariable long id){
//		return pessoarep.findById(id)
//				.map(resp -> ResponseEntity.ok(resp))
//				.orElse(ResponseEntity.notFound().build());
//	}
//	
//	@GetMapping("/usuarios/{nome}")
//	public ResponseEntity<List<Pessoa>> buscaPorNome (@PathVariable String nome){
//		List<Pessoa> pessoa = pessoarep.findByNomeContainingIgnoreCase(nome);
//		
//		if(pessoa.size()>0) {
//			return ResponseEntity.ok(pessoa);
//		} else {
//			return new ResponseEntity<List<Pessoa>>(HttpStatus.NO_CONTENT);
//		}	
//	}
//	
//	@GetMapping("/usuario/email/{email}")
//	public ResponseEntity<Pessoa> buscaPorEmail (@PathVariable String email){
//		Pessoa user = pessoarep.findByEmailContainingIgnoreCase(email);
//		if(user != null) {
//			return ResponseEntity.ok(user);
//		} else {
//			return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
//		}
//	}	
//	
//	@PostMapping
//	public ResponseEntity<String> cadastro (@RequestBody Pessoa usuario){
//				
//		try {
//			pessoarep.save(usuario);
//			return ResponseEntity.status(HttpStatus.CREATED).body(null);
//		}
//		catch(Exception e) {
//			if(e.getCause() != null) {
//				ConstraintViolationException e1 = (ConstraintViolationException) e.getCause();
//				SQLIntegrityConstraintViolationException e2 = (SQLIntegrityConstraintViolationException) e1.getCause();
//				return new ResponseEntity<String>("CPF ou email já cadastrado(s) " + e2.getMessage(), HttpStatus.BAD_REQUEST);
//			} else {
//				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//			}
//		}
//	}
//	
//	@PostMapping("/logar")
//	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
//		return pessoaService.Logar(user).map(resp -> ResponseEntity.ok(resp))
//				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
//	}
//
//	@PostMapping("/cadastrar")
//	public ResponseEntity<Pessoa> Post(@RequestBody Pessoa usuario) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.CadastrarUsuario(usuario));
//	}
//	
//	@PutMapping
//	public ResponseEntity<Pessoa> alteracao (@RequestBody Pessoa usuario){
//		return ResponseEntity.status(HttpStatus.OK)
//		.body(pessoarep.save(usuario));
//	}
//
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable long id) {
//		pessoarep.deleteById(id);
//	}
//}
