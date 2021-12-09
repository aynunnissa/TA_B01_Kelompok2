package apap.TugasAkhir.siFactory.restcontroller;

import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;
import apap.TugasAkhir.siFactory.rest.BaseResponse;
import apap.TugasAkhir.siFactory.rest.RequestDTO;
import apap.TugasAkhir.siFactory.service.RequestUpdateItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1")
public class RequestUpdateItemRestController {

    @Autowired
    private RequestUpdateItemRestService requestUpdateItemRestService;

    @PostMapping(value = "/requestUpdateItem/create")
    private BaseResponse<RequestUpdateItemModel> createRequestUpdateItem(
            @Valid @RequestBody RequestDTO requestUpdateItem,
            BindingResult bindingResult) throws ParseException {
        BaseResponse<RequestUpdateItemModel> response = new BaseResponse<>();
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            try {
                RequestUpdateItemModel request = requestUpdateItemRestService
                        .createRequestUpdateItem(requestUpdateItem);
                response.setStatus(201);
                response.setMessage("Request berhasil");
                response.setResult(request);
            } catch (Exception e) {
                response.setStatus(400);
                response.setMessage(e.toString());
                response.setResult(null);
            }
            return response;
        }
    }
}
