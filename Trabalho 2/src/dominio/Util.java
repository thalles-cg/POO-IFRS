package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    public static boolean validarData(LocalDate data) throws Exception {
        if (data.isBefore(LocalDate.now())){
            throw new DataInvalidaException(Util.getDataFormatada(data) + " é inválido, pois é anterior à data atual");
        }
        return true;
    }

    public static void validarDataHorario (Medico medico, LocalDate data, int horario) throws Exception {
        Util.validarData(data);
        if(!medico.getAgendaDisponivel().containsKey(data)) {
            throw new AgendaNaoDiponivelException("A agenda do " + medico.getNome() + " não foi aberta para " + Util.getDataFormatada(data));
        };
        if (!medico.getAgendaDisponivel().get(data).contains(horario)){
            throw new AgendaNaoDiponivelException(horario + "h de " + Util.getDataFormatada(data) + " não está disponível na agenda do " + medico.getNome());
        }
    }

    public static String getDataFormatada(LocalDate data){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getData_horaFormatada(LocalDate data, int horario) {
        return Util.getDataFormatada(data) + " as " + horario + "h";
    }
}
