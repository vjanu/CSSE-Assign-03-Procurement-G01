package sliit.g01.procurementg01.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anushka
 */
@RestController
public class RootController {

	@GetMapping("/")
	public String index() {
		// index.html is at src/main/resources/static.
		// we can reference anything at static directory as if we are in that
		// directory.
		return "<script type='text/javascript'> window.location.href = 'index.html'; </script>"; // this
																									// will
																									// ask
																									// the
																									// browser
																									// to
																									// redirect
																									// to
																									// the
																									// given
																									// url.
	}

	@GetMapping("/error")
	public String error() {
		return "<script type='text/javascript'> window.location.href = 'error.html'; </script>"; // this
																									// will
																									// ask
																									// the
																									// browser
																									// to
																									// redirect
																									// to
																									// the
																									// given
																									// url.
	}
}
