package dominio;

import java.time.LocalDate;

public class Util {

    public static boolean validarData(LocalDate data){
        if (data.isBefore(LocalDate.now())){
            throw new DataInvalidaException("A data informada é anterior à data atual");
        }
        return true;
    }

    public static void validarDataHorario (Medico medico, LocalDate data, int horario) throws Exception {
        Util.validarData(data);
        if(!medico.getAgendaDisponivel().containsKey(data)) {
            throw new AgendaNaoDiponivelException("A agenda do " + medico.getNome() + " não foi aberta para " + data);
        };
        if (!medico.getAgendaDisponivel().get(data).contains(horario)){
            throw new AgendaNaoDiponivelException(horario + "h de " + data + " não está disponível na agenda do " + medico.getNome());
        }
    }
}
