package apap.TugasAkhir.siFactory.rest;

import java.time.LocalDate;

import apap.TugasAkhir.siFactory.model.RequestUpdateItemModel;

public class RequestDTO {
    public String idItem;
    public int idKategori;
    public int tambahanStok;
    public LocalDate tanggalRequest;
    public int idCabang;

    public RequestUpdateItemModel convertToRequestUpdateItemModel() {
        RequestUpdateItemModel request = new RequestUpdateItemModel();
        request.setIdItem(idItem);
        request.setIdKategori(idKategori);
        request.setTambahanStok(tambahanStok);
        request.setTanggalRequest(tanggalRequest);
        request.setIdCabang(idCabang);
        return request;
    }

    public RequestUpdateItemModel convertToRequestUpdateItemModel(RequestUpdateItemModel request) {
        request.setIdItem(idItem);
        request.setIdKategori(idKategori);
        request.setTambahanStok(tambahanStok);
        request.setTanggalRequest(tanggalRequest);
        request.setIdCabang(idCabang);
        return request;
    }
}
