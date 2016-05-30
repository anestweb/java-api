<%-- 
    Document   : nova
    Created on : 24/05/2016, 22:37:34
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>
    <t:paciente_etiqueta />

    <form action="${actionUrl}" method="post"
          enctype="application/x-www-form-urlencoded"
          accept-charset="utf-8">
        <div class="form">

            <table>
                <thead>
                    <tr><th colspan="6" class="center aligned">AVALIAÇÃO FÍSICA</th></tr>
                </thead>
            </table>
            <div class="flex-row">
                <div class="field grid-2">
                    <label for="peso">Peso</label>
                    <input type="text" id="peso" name="peso" value="">
                </div>

                <div class="field grid-2">
                    <label for="altura">Altura</label>
                    <input type="text" id="altura" name="peso" value="">
                </div>

                <div class="field grid-2">
                    <label for="pa">Pressão Arterial (PA)</label>
                    <input type="text" id="pa" name="peso" value="">
                </div>

                <div class="field grid-10">
                    <label for="palidez">Palidez</label>
                    <input type="text" id="pa" name="palidez" value=""
                           placeholder="hidratado/corado/eupnéico/etc.">
                </div>
            </div>
            <div class="flex-row">
                <div class="field grid-2">
                    <label for="dentadura">Dentadura/Prótese</label>
                    <input type="text" id="dentadura" name="dentadura" value=""
                           placeholder="inferior/superior, móvel/fixa">
                </div>

                <div class="field grid-2">
                    <label for="dentes">Dentes</label>
                    <input type="text" id="dentes" name="dentes" value="">
                </div>

                <div class="field grid-2">
                    <label for="boca">Abertura de boca</label>
                    <input type="text" id="boca" name="boca" value="">
                </div>

                <div class="field grid-5">
                    <label for="pescoco">Pescoço</label>
                    <input type="text" id="pescoco" name="pescoco" value=""
                           placeholder="normal/curto">
                </div>

                <div class="field grid-5">
                    <label for="pescoco">Flexão/Extensão do pescoço</label>
                    <input type="text" id="pescoco" name="pescoco" value=""
                           placeholder="normal/limitada">
                </div>
            </div>
            <div class="flex-row">
                <div class="field grid-1">
                    <label for="mallampati">Mallampati</label>
                    <select name="mallampati" id="mallampati" required>
                        <option value=""></option>
                        <option value="I">I</option>
                        <option value="II">II</option>
                        <option value="III">III</option>
                        <option value="IV">IV</option>
                    </select>
                </div>

                <div class="field grid-15">
                    <label for="anotacao_fisico">Anotação Complementar da Avaliação Física</label>
                    <input type="text" id="anotacao_fisico" name="anotacao_fisico" value="">
                </div>
            </div>

            <table>
                <thead>
                    <tr><th colspan="6" class="center aligned">OUTRAS INFORMAÇÕES</th></tr>
                </thead>
            </table>
            <div class="flex-row">
                <div class="field grid-16">
                    <label for="y3">Anotações Complementares</label>
                    <textarea id="" name="anotacoes"></textarea>
                </div>
            </div>
        </div>

        <div class="actions">
            <button type="submit" class="primary button"><i class="fa fa-save fa-fw"></i>Salvar</button>
            <a href="./pacientes/historico?id=${paciente.getId()}" class="button left floated">
                <i class="fa fa-arrow-left fa-fw"></i> Cancelar e retornar à ficha do paciente</a>
            <div class="clearfix"></div>
        </div>
    </form>

    <div class="divider hidden"></div>

    <t:paciente_ficha />

</t:mainlayout>
