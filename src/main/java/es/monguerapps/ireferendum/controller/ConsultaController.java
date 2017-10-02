package es.monguerapps.ireferendum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import es.monguerapps.ireferendum.beans.Consulta;
import es.monguerapps.ireferendum.service.IConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private IConsultaService consultaService;

	@PostMapping()
	public ResponseEntity<Void> addConsulta(@RequestBody Consulta consulta, UriComponentsBuilder builder) {
		boolean flag = consultaService.addConsulta(consulta);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/article/{id}").buildAndExpand(consulta.getIdConsulta()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Consulta> getConsulta(@PathVariable("id") Integer id) {
		return new ResponseEntity<Consulta>(consultaService.getConsultaById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "avanzar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Consulta> avanzarConsulta(@PathVariable("id") Integer id) {
		return new ResponseEntity<Consulta>(consultaService.avanzarConsulta(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Consulta>> getAllConsultas() {
		List<Consulta> list = consultaService.getAllConsultas();
		return new ResponseEntity<List<Consulta>>(list, HttpStatus.OK);
	}
	

}
