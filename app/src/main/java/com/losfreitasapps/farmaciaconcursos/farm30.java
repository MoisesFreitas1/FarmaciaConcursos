package com.losfreitasapps.farmaciaconcursos;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

/**
 * Created by Moises on 18/05/16.
 */
public class farm30 extends Fragment {
    private TextView questaoTextView;
    private TextView textView;
    private RadioButton a;
    private RadioButton b;
    private RadioButton c;
    private RadioButton d;
    private RadioButton e;
    private RadioGroup rg;
    private int opcao;
    private int tentativas;
    private int alternativa;
    int[] nquestion;
    int nquestions;
    int m;
    InterstitialAd mInterstitialAd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_radiologia, container, false);

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-6303877676651382/5422059755");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();

        nquestions = 191;
        m = 0;
        tentativas = 0;
        textView = (TextView) view.findViewById(R.id.textView);
        questaoTextView = (TextView) view.findViewById(R.id.questaoTextView);
        rg = (RadioGroup) view.findViewById(R.id.rgopcoes);
        a = (RadioButton) view.findViewById(R.id.a);
        b = (RadioButton) view.findViewById(R.id.b);
        c = (RadioButton) view.findViewById(R.id.c);
        d = (RadioButton) view.findViewById(R.id.d);
        e = (RadioButton) view.findViewById(R.id.e);
        nquestion = new int[30];
        int tquestions[];
        int aux;
        Random random = new Random();
        tquestions = new int[nquestions];
        for (int b = 0; b < nquestions; b++) {
            tquestions[b] = b + 1;
        }
        for (int n = 0; n < 30; n++) {
            do {
                aux = random.nextInt(nquestions);
                nquestion[n] = tquestions[aux];
            } while (tquestions[aux] == 0);
            tquestions[aux] = 0;
        }


        alternativa = update(nquestion[m]);
        textView.setText((m + 1) + " de " + nquestion.length);

        Button button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        opcao = rg.getCheckedRadioButtonId();
                        alternativa = update(nquestion[m]);
                        textView.setText((m + 1) + " de " + nquestion.length);
                        if (opcao != alternativa) {
                            tentativas = tentativas + 1;
                            AlertDialog.Builder mensagem = new AlertDialog.Builder(getActivity());
                            mensagem.setTitle("Atenção!");
                            mensagem.setMessage("Alternativa incorreta");
                            mensagem.setNeutralButton("OK", null);
                            mensagem.show();
                        }

                        if (opcao == alternativa) {
                            if (m == (nquestion.length-1)) {
                                tentativas = tentativas + 1;
                                AlertDialog.Builder mensagem1 = new AlertDialog.Builder(getActivity());
                                mensagem1.setTitle("   ESTATÍSTICAS");
                                mensagem1.setMessage(tentativas + " tentativas para responder " + nquestion.length + " questões");
                                mensagem1.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (mInterstitialAd.isLoaded()) {
                                            mInterstitialAd.show();
                                        }
                                    }
                                });
                                mensagem1.show();
                            }

                            if (m < (nquestion.length - 1)) {
                                tentativas = tentativas + 1;
                                AlertDialog.Builder mensagem = new AlertDialog.Builder(getActivity());
                                mensagem.setTitle("Parabéns!");
                                mensagem.setMessage("Alternativa correta");
                                mensagem.setNeutralButton("OK", null);
                                mensagem.show();
                                m = m + 1;
                                alternativa = update(nquestion[m]);
                                textView.setText((m + 1) + " de " + nquestion.length);
                            }
                        }
                    }
                });
        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if (m > 0) {
                            m = m - 1;
                            alternativa = update(nquestion[m]);
                            textView.setText((m + 1) + " de " + nquestion.length);
                        } else {
                            Toast.makeText(getActivity(), "Início do Teste", Toast.LENGTH_SHORT).show();

                        }
                    }

                });


        return view;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }


    private int update(int question) {
        int alt = -1;
        if (question == 2) {
            questaoTextView.setText("Um dos componentes importantes da gestão do estoque de medicamentos é a gestão da demanda, que, segundo Proud, significa a necessidade de um produto ou componente particular. Existem vários tipos de demanda, que devem ser conhecidos para que se possa realizar uma análise e identificar em qual deles os medicamentos disponíveis se enquadram. \nAssinale a alternativa que define corretamente o tipo de demanda.");
            a.setText("Permanente: para produtos com vida longa, requer ressuprimento periódico.");
            b.setText("Irregular: ligada a outro produto, pode aumentar ou diminuir.");
            c.setText("Derivada: depende de outros fatores como taxas de câmbio, promoção e propaganda.");
            d.setText("Sazonal: ocorre quando a demanda do produto acaba ou um novo produto ocupa o seu lugar.");
            e.setText("Em desuso: quando existe sazonalidade, como resultado de causas naturais, econômicas, sociais e institucionais.");
            alt = R.id.a;
        }

        if (question == 1) {
            questaoTextView.setText("O ponto de pedido ou de ressuprimento (PP) equivale a uma quantidade de determinado produto, existente no estoque e que determina a emissão de um novo pedido de compra a fim de evitar a ruptura do estoque e a diminuição da qualidade do atendimento. Considere um item que tem consumo médio de 100 unidades por mês, que o estoque mínimo mantido é para 2 meses e que seu tempo de reposição é de 2 meses. O ponto de pedido desse produto, em unidades, deve ser");
            a.setText("100.");
            b.setText("200.");
            c.setText("300.");
            d.setText("400.");
            e.setText("500.");
            alt = R.id.d;
        }
        if (question == 3) {
            questaoTextView.setText("Os sistemas de distribuição de medicamentos que apresentam como vantagens maior facilidade de acesso aos medicamentos e menor quantidade de erros de medicação são, respectivamente, o sistema");
            a.setText("coletivo e o sistema individualizado.");
            b.setText("coletivo e o sistema por dose unitária.");
            c.setText("individualizado e o sistema coletivo.");
            d.setText("individualizado e o sistema por dose unitária.");
            e.setText("por dose unitária e o sistema coletivo.");
            alt = R.id.b;
        }
        if (question == 4) {
            questaoTextView.setText("O farmacêutico tem um importante papel no controle de infecções hospitalares. Entre suas funções está a de identificar a contaminação de medicamentos. Existem dificuldades para a identificação de surtos por contaminação de medicamentos, em decorrência de suas características epidemiológicas e, principalmente, da inexistência de protocolos específicos que permitam a caracterização e o estudo. \nEm relação a esse tipo de contaminação, é correto afirmar que");
            a.setText("se denomina contaminação intrínseca a contaminação que ocorre na indústria, sendo geralmente disseminada no tempo, espaço e entre várias instituições.");
            b.setText("se denomina contaminação na farmácia a que ocorre com medicações utilizadas em doses múltiplas, preparadas em postos de enfermagem a partir de produtos de múltipla dose mal manipulados ou conservados inadequadamente após sua abertura.");
            c.setText("se denomina contaminação no local de uso aquela que envolve a contaminação de soluções usadas na formulação de um produto ou um equipamento, frequentemente bomba de infusão ou seringa usados na formulação ou preparação de um fluído estéril.");
            d.setText("para evitar a contaminação na farmácia, é importante centralizar as práticas de manipulação, ou quando isso não for possível, que estas atividades sejam desenvolvidas sob a orientação desse departamento.");
            e.setText("a contaminação no local de uso só pode ser identificada se um rigoroso sistema de vigilância for implantado, mas pode ser suspeitada todas as vezes que um agente não usual for identificado em uma topografia também não usual.");
            alt = R.id.a;
        }
        if (question == 5) {
            questaoTextView.setText("Uma NPT típica para adulto costuma conter");
            a.setText("500-700 mL de uma solução a 5% de aminoácidos.");
            b.setText("100-200 mL de uma solução a 30% de glicose.");
            c.setText("50-100 mL de uma solução a 20% de lipídeos TCL/TCM.");
            d.setText("10-20 mL de uma solução a 20% de NaCl.");
            e.setText("30 mL de uma solução multivitamínica.");
            alt = R.id.d;
        }
        if (question == 6) {
            questaoTextView.setText("Assinale a alternativa que apresenta o conceito em epidemiologia corretamente definido.");
            a.setText("Mortalidade refere-se ao conjunto dos indivíduos que adquirem doenças (ou determinadas doenças) num dado intervalo de tempo em uma determinada população. A mortalidade mostra o comportamento das doenças e dos agravos à saúde na população.");
            b.setText("Morbidade refere-se ao conjunto dos indivíduos que morreram num dado intervalo do tempo e representa o risco ou probabilidade que qualquer pessoa na população apresenta de poder vir a morrer ou de morrer em decorrência de uma determinada doença.");
            c.setText("Endemia é a ocorrência de determinada doença que acomete sistematicamente populações em espaços característicos e determinados, no decorrer de um longo período, (temporalmente ilimitada), e que mantém uma incidência relativamente constante, permitindo variações cíclicas e sazonais.");
            d.setText("Prevalência de uma doença é o número de casos novos da doença que iniciaram no mesmo local e período. Traz a ideia de intensidade com que acontece uma doença numa população, mede a frequência ou probabilidade de ocorrência de casos novos de doença na população.");
            e.setText("Incidência é o número total de casos de uma doença existentes num determinado local e período.");
            alt = R.id.c;
        }
        if (question == 7) {
            questaoTextView.setText("A Anvisa regulamentou a utilização de agentes antimicrobianos em diferentes âmbitos. Considerando essa regulamentação, assinale a alternativa correta em relação à utilização desses agentes.");
            a.setText("Não é permitida associação de limpadores com sanitizantes/ desinfetantes.");
            b.setText("Em lactários, não poderão ser utilizados como princípios ativos substâncias inorgânicas liberadoras de cloro ativo e hipocloritos de sódio, lítio ou cálcio.");
            c.setText("Não são permitidas nas composições de desinfetantes hospitalares para superfícies fixas os princípios ativos: formaldeído, paraformaldeído, glutaraldeído e glioxal.");
            d.setText("Produtos para saúde utilizados na assistência ventilatória e inaloterapia poderão ser submetidos à desinfecção por métodos de imersão química líquida com a utilização de saneantes à base de aldeídos.");
            e.setText("Na esterilização de produtos para a saúde, é permitido o uso de estufa e de autoclave gravitacional de capacidade superior a 100 litros.");
            alt = R.id.c;
        }
        if (question == 8) {
            questaoTextView.setText("Para o bom funcionamento de uma Farmácia Hospitalar, cada unidade deve ser estruturada de maneira adequada. A unidade de Manipulação Magistral e Oficinal deve contemplar o seguinte requisito:");
            a.setText("área de lavagem de utensílios e materiais: 10 m²");
            b.setText("área de manipulação de sólidos: 9 m²");
            c.setText("área de manipulação de semissólidos e líquidos: 15 m²");
            d.setText("área do controle de qualidade: 10 m²");
            e.setText("área de esterilização: 6 m²");
            alt = R.id.b;
        }
        if (question == 9) {
            questaoTextView.setText("Para assegurar a administração de doses corretas, cada unidade do lote de um medicamento deve conter quantidade do componente ativo próxima da quantidade declarada. Os testes permitem avaliar a quantidade de componente ativo em unidades individuais do lote e verificar se essa quantidade é uniforme nas unidades testadas. \nAssinale a alternativa que descreve corretamente o método usado de acordo com a forma farmacêutica.");
            a.setText("Para comprimidos não revestidos, usar o método da Uniformidade de Conteúdo para doses ≥ 25 mg ou ≥ 25% e o método da Variação de Peso para doses < 25 mg ou < 25%.");
            b.setText("Para comprimidos revestidos por filme, usar o método da Uniformidade de Conteúdo para doses ≥ 25 mg ou ≥ 25% e o método da Variação de Peso para doses < 25 mg ou < 25%.");
            c.setText("Para cápsulas moles, preenchidas com soluções, usar sempre o método da Uniformidade de Conteúdo.");
            d.setText("Para sólidos acondicionados em recipientes para dose única de um único componente, usar sempre o método da Uniformidade de Conteúdo.");
            e.setText("Para soluções acondicionadas em recipientes para dose única, usar sempre o método da Variação de Peso.");
            alt = R.id.e;
        }
        if (question == 10) {
            questaoTextView.setText("Muitas substâncias farmacopeicas encontram-se na forma hidratada ou contêm água absorvida, tornando-se relevante a determinação de água por métodos específicos, entre eles o método volumétrico direto. Esse método");
            a.setText("é realizado em um aparelho de titulação de capacidade de 60 mL, munido de 2 eletrodos de platina, de um tubo de admissão para o nitrogênio, de uma rolha adaptada à extremidade de uma bureta e de um tubo de admissão de ar protegido por um agente de secagem.");
            b.setText("possibilita a determinação de água contida em amostras de natureza múltipla. A água presente é destilada com tolueno, solvente no qual é praticamente imiscível, e separada em tubo receptor apropriado após resfriamento.");
            c.setText("baseia-se na reação quantitativa da água com solução anidra de dióxido de enxofre e iodo em presença de uma solução tampão que reage com íons hidrogênio. Na solução volumétrica, original, conhecida como Reagente de Karl Fischer, o dióxido de enxofre e o iodo são dissolvidos em piridina e metanol.");
            d.setText("incorpora um excesso de Reagente de Karl Fischer à amostra e, após aguardar o tempo necessário à reação quantitativa, titula-se o excesso de reagente com solução padrão de água em metanol.");
            e.setText("utiliza o reagente de Karl Fischer, no entanto o iodo não é utilizado como solução volumétrica, mas obtido por oxidação anódica de uma solução contendo iodeto.");
            alt = R.id.c;
        }
        if (question == 11) {
            questaoTextView.setText("Água para injetáveis ou misturas endovenosas é o insumo utilizado na preparação de medicamentos para administração parenteral, como veículo ou na dissolução ou diluição de substâncias ou de preparações. \nAssinale a alternativa correta em relação aos quesitos que a água para injetáveis precisa cumprir.");
            a.setText("Os parâmetros de condutividade elétrica e carbono orgânico total não necessitam ser testados.");
            b.setText("A determinação da contaminação por partículas subvisíveis pode ser feita por 2 métodos: ensaio de contagem de partículas por bloqueio da luz ou ensaio de contagem de partículas por microscopia óptica.");
            c.setText("A contagem do número total de micro-organismos mesófilos deve ser realizada com, pelo menos, 1 000 mL de amostra e ter, no máximo, 100 UFC/mL.");
            d.setText("Deve ser realizada a detecção de endotoxinas bacterianas, sendo permitido, no máximo, 0,50 UI/mL.");
            e.setText("Após esterilização, pode ser armazenada em recipiente inerte, como o aço inox 316L polido, mantido fechado, com temperatura de 20 – 25 °C, sob recirculação, por um período máximo de 48 horas.");
            alt = R.id.b;
        }
        if (question == 12) {
            questaoTextView.setText("Quanto às infrações e sanções disciplinares estabelecidas pelo Código de Ética da Profissão Farmacêutico, pode-se afirmar que");
            a.setText("a verificação do cumprimento das normas estabelecidas nesse Código é atribuição exclusiva da Comissão de Ética dos Conselhos Regionais de Farmácia.");
            b.setText("a apuração das infrações éticas compete ao Conselho Federal de Farmácia em que o profissional estiver inscrito, ao tempo do fato punível em que incorreu.");
            c.setText("prescreve em 12 meses a constatação fiscal de ausência do farmacêutico no estabelecimento, por meio de auto de infração ou termo de visita, para efeito de instauração de processo ético.");
            d.setText("o profissional condenado por sentença criminal transitada em julgado em razão do exercício da profissão ficará “ex officio” suspenso da atividade, enquanto durar a execução da pena.");
            e.setText("o farmacêutico portador de doença que o incapacite ao exercício da profissão farmacêutica, atestada em instância administrativa, judicial ou médica, e certificada pelo Conselho Regional de Farmácia, terá o seu registro e as suas atividades profissionais caçados.");
            alt = R.id.d;
        }
        if (question == 13) {
            questaoTextView.setText("A padronização de medicamentos (PM) é empregada desde os anos 50 nos EUA para assegurar o correto uso dos medicamentos e garantir maior resolutividade institucional e economia de recursos, sem comprometimento da qualidade. Os tipos de PM estão diretamente vinculados ao sistema de gestão da organização, ao seu tamanho, aos objetivos de seus serviços, às provisões de medicamentos, às especialidades médicas e aos recursos orçamentários.\nAssinale a alternativa correta em relação à PM.");
            a.setText("Na padronização aberta, existe uma listagem de medicamentos previamente definida, e não há fornecimento de outros medicamentos que não estejam incluídos na listagem.");
            b.setText("Na padronização aberta, fármacos mais caros ou que requeiram um acompanhamento especial somente podem ser prescritos por médicos de determinadas especialidades.");
            c.setText("A padronização fechada não permite o uso de fármacos ausentes da listagem dos medicamentos selecionados sob nenhuma hipótese.");
            d.setText("A padronização fechada é frequentemente utilizada em hospitais em fase de implantação.");
            e.setText("A padronização seletiva ou parcialmente fechada é essencialmente uma padronização aberta, na qual há negativa de aquisição de medicamentos para determinados propósitos, como cosmético ou perda de peso, por exemplo.");
            alt = R.id.e;
        }
        if (question == 14) {
            questaoTextView.setText("Quando um medicamento é retirado de sua embalagem original para fracionamento, o prazo de validade desse medicamento, conforme recomendado,");
            a.setText("deve manter-se igual ao prazo de validade da embalagem original, desde que não tenha sido alterada a embalagem primária.");
            b.setText("deve manter-se igual ao prazo de validade da embalagem primária, desde que não tenha sido rompida a embalagem secundária.");
            c.setText("deve ser superior a 25% do tempo compreendido entre a data de fracionamento e a data de validade do fabricante; esse tempo não deve ser maior que 12 meses.");
            d.setText("não deve ser superior a 25% do tempo compreendido entre a data de fracionamento e a data de validade do fabricante; esse tempo não deve ser maior que 6 meses.");
            e.setText("não deve ser superior a 25% do tempo compreendido entre a data de fracionamento e a data de validade do fabricante; esse tempo não deve ser maior que 1 ano.");
            alt = R.id.d;
        }
        if (question == 15) {
            questaoTextView.setText("Os medicamentos somente são eficazes se houver garantia de que, desde sua fabricação até o seu uso, sejam armazenados, transportados e manuseados em condições adequadas. Dessa forma estarão preservadas a sua qualidade, eficácia e segurança. A área de ambiente controlado é a sala do almoxarifado onde a temperatura deve ser mantida entre");
            a.setText("10 e 20 ºC para estocagem de produtos cujo acondicionamento primário não os protege da umidade. A umidade deve ser mantida entre 30 e 50%.");
            b.setText("15 e 30 ºC para estocagem de produtos cujo acondicionamento primário não os protege da umidade. A umidade deve ser mantida entre 40 e 70%.");
            c.setText("15 e 30 ºC para estocagem de produtos cujo acondicionamento primário não os protege da umidade. A umidade deve ser mantida entre 30 e 50%.");
            d.setText("2 e 8 ºC para estocagem de produtos cujo acondicionamento primário não os protege da umidade. A umidade deve ser mantida entre 40 e 70%.");
            e.setText("2 e 8 ºC para estocagem de produtos cujo acondicionamento primário não os protege da umidade. A umidade deve ser mantida entre 60 e 70%.");
            alt = R.id.b;
        }
        if (question == 16) {
            questaoTextView.setText("Inventário é a contagem de todos os itens em estoque de um almoxarifado, a fim de se verificar se a quantidade encontrada nas prateleiras coincide com os valores informados nas fichas de controle. Recomenda-se que sua realização seja");
            a.setText("periódica, mensal, com amostras seletivas de 5 a 10% dos produtos em estoque e dos itens de maior rotatividade e registro das irregularidades encontradas.");
            b.setText("periódica, quinzenal, com amostras seletivas de 10 a 20% dos produtos em estoque e dos itens de maior rotatividade e registro das irregularidades encontradas.");
            c.setText("periódica, semanal, com amostras seletivas de 10 a 20% dos produtos em estoque e dos itens de maior rotatividade e registro das irregularidades encontradas.");
            d.setText("obrigatória, para 30% dos itens a cada três meses.");
            e.setText("obrigatória, para 50% dos itens a cada doze meses.");
            alt = R.id.c;
        }
        if (question == 17) {
            questaoTextView.setText("Com a RDC no. 3/2015, a substância canabidiol (CBD) passou a ter seu uso permitido no Brasil para o tratamento de epilepsia que é refratária ao tratamento com outras drogas. Sobre o CBD e seu uso, é correto afirmar que");
            a.setText("com essa resolução da Anvisa, o CBD deixa de ser uma substância proscrita (lista F2) e passa a integrar a lista das substâncias sujeitas a controle especial (C1).");
            b.setText("com essa resolução da Anvisa, o CBD já pode ser produzido e comercializado no Brasil, facilitando o acesso desse medicamento a um grande número de crianças e adultos que dependem dele para evitar as crises epilépticas.");
            c.setText("o CBD atua nos receptores canabinoides do SNC, produzindo uma mescla de efeitos psicomiméticos e depressores.");
            d.setText("a principal vantagem do uso do CBD para o tratamento da epilepsia é que somente o THC, seu principal metabólito, apresenta atividade anticonvulsivante.");
            e.setText("apesar de o CBD e de o THC serem derivados da mesma planta, a Cannabis sativa, e serem substâncias ativas distintas, ambos agem bloqueando os receptores canabinoides CB1 e CB2 e apresentam os mesmos efeitos antipsicóticos.");
            alt = R.id.a;
        }
        if (question == 18) {
            questaoTextView.setText("Fármacos que são inibidores da ciclo-oxigenase (COX) podem apresentar três efeitos terapêuticos principais que são fundamentados na inibição da síntese do ácido araquidônico: anti-inflamatório, analgésico e antipirético. Quanto a esses fármacos, é correto afirmar que");
            a.setText("todos os inibidores de COX apresentam igualmente os 3 efeitos terapêuticos, uma vez que agem sobre o mesmo sítio de ação.");
            b.setText("os coxibes geralmente possuem grupamento sulfonamida ou sulfona que impedem o acesso da molécula ao canal hidrofóbico da enzima COX-1, o que os torna mais seletivos para COX-2.");
            c.setText("o efeito terapêutico principal está mais relacionado à inibição da COX-1, enquanto os efeitos colaterais estão mais relacionados com a inibição da COX-2.");
            d.setText("o paracetamol apresenta forte ação anti-inflamatória com menos efeitos tóxicos e, por esse motivo, é o medicamento de escolha para o tratamento em crianças e gestantes.");
            e.setText("o mecanismo da ação antipirética está relacionado, principalmente, com a inibição da síntese do ácido araquidônico nos sistemas nervoso central e periférico.");
            alt = R.id.b;
        }
        if (question == 19) {
            questaoTextView.setText("Fármacos vasodilatadores podem ser usados na terapia anti-hipertensiva de forma isolada ou combinada com fármacos de outras classes terapêuticas. \nAssinale a alternativa que define corretamente o mecanismo de ação do fármaco vasodilatador apresentado.");
            a.setText("Nifedipino é um antagonista de cálcio e age preferencialmente sobre a musculatura lisa.");
            b.setText("Verapamil age relaxando a musculatura lisa por indução de abertura dos canais de potássio.");
            c.setText("Minoxidil age diretamente sobre o coração com efeitos inotrópicos e cronotrópicos negativos.");
            d.setText("Captopril age indiretamente sobre a pressão arterial, bloqueando os receptores de angiotensina At1 e At2.");
            e.setText("Losartana age indiretamente sobre a pressão arterial, pela inibição da enzima conversora de angiotensina.");
            alt = R.id.a;
        }
        if (question == 20) {
            questaoTextView.setText("A absorção é definida como a passagem de um fármaco de seu local de administração para o plasma. Assinale a alternativa correta quanto à relação entre a via de administração de um fármaco e a sua absorção.");
            a.setText("A absorção é um fenômeno que deverá ocorrer com todos os tipos de fármacos, administrados por qualquer via, para que esse fármaco possa chegar ao seu sítio de ação e exercer sua função.");
            b.setText("No intestino, fármacos que são bases fortes e com elevado pKa são os mais rapidamente absorvidos.");
            c.setText("A administração retal é útil apenas para fármacos de ação local, ou seja, que são absorvidos e agem diretamente no reto ou intestino.");
            d.setText("Fármacos administrados por via sublingual podem passar diretamente para a circulação sistêmica, sem entrar pelo sistema porta-hepático.");
            e.setText("A via inalatória somente é útil para a administração de fármacos de ação local, ou seja, que exercem seu efeito nos pulmões, e geralmente a administração é feita por inalação.");
            alt = R.id.d;
        }
        if (question == 21) {
            questaoTextView.setText("No desenvolvimento e preparo de cápsulas de gelatina dura, deve-se");
            a.setText("optar sempre por operações automatizadas, que são mais precisas e confiáveis.");
            b.setText("ter um cuidado ainda maior na mistura e homogeneização de fármacos que são usados em alta dosagem, em relação aos de baixa dosagem, pois qualquer erro pode resultar em consequências terapêuticas graves.");
            c.setText("optar pela adição de um diluente que proporcione um volume adequado na cápsula e permita maior coesão entre os pós, facilitando a transferência para o interior dos invólucros.");
            d.setText("optar pela adição de um desintegrante, como a celulose micro cristalina, que auxilia na ruptura do invólucro e na distribuição do conteúdo no estômago.");
            e.setText("garantir que o tamanho das partículas e a densidade dos componentes não ativos sejam maiores que do fármaco empregado, a fim de atingir uma distribuição mais uniforme.");
            alt = R.id.c;
        }
        if (question == 22) {
            questaoTextView.setText("Formas farmacêuticas com liberação prolongada têm sido utilizadas, muitas vezes, no lugar das formulações convencionais. Uma forma farmacêutica de liberação prolongada pode reduzir os efeitos colaterais em relação à formulação convencional de um mesmo fármaco porque");
            a.setText("o princípio ativo fica adsorvido a uma outra molécula que o libera continuamente, o que diminui possíveis interações com outros fármacos, e com isso há uma menor incidência de efeitos colaterais.");
            b.setText("com menor frequência de administração, o risco de esquecimento das doses é menor e, com isso, também é menor a incidência de efeitos colaterais.");
            c.setText("com menor frequência de administração, são ingeridas doses menores do fármaco e, com isso, também é menor a incidência de efeitos colaterais.");
            d.setText("são liberadas dosagens maiores do fármaco em intervalos menores e, com isso, há redução dos níveis plasmáticos e menor incidência de efeitos colaterais.");
            e.setText("podem ser reduzidos os picos de concentração sanguínea acima dos níveis terapêuticos ou em níveis tóxicos e, com isso, os efeitos colaterais tendem a ser menores.");
            alt = R.id.e;
        }
        if (question == 23) {
            questaoTextView.setText("Com relação aos sistemas transdérmicos de liberação de fármacos (TTSs), é correto afirmar que");
            a.setText("o dispositivo de liberação do fármaco é muito importante, pois o estrato córneo da pele não exerce influência sobre a velocidade de absorção do fármaco.");
            b.setText("a velocidade de transporte do fármaco em todos os tipos de TTSs, monolíticos ou com membranas, deve ser sempre controlada por uma membrana artificial.");
            c.setText("a maioria dos TTSs é projetada para conter excesso de fármaco e manter a capacidade de liberação além do tempo recomendado para a substituição do adesivo.");
            d.setText("o sistema monolítico de liberação de fármacos apresenta uma membrana controladora da liberação.");
            e.setText("no sistema transdérmico controlado por membrana, o fármaco é incorporado em uma camada matricial, composta por material polimérico que controla a liberação do fármaco.");
            alt = R.id.c;
        }
        if (question == 24) {
            questaoTextView.setText("O Programa Farmácia Popular do Brasil (PFPB) é uma iniciativa para cumprimento de diretrizes da Política Nacional de Assistência Farmacêutica. A Portaria no. 184/2011 dispõe sobre o PFPB que inclui os programas“Aqui tem farmácia popular” e “Rede própria”. Sobre o programa “Rede própria”, é correto afirmar que");
            a.setText("a dispensação dos medicamentos ocorrerá mediante o ressarcimento correspondente, tão somente aos custos de produção ou aquisição, distribuição e dispensação, conforme valores de dispensação estabelecidos.");
            b.setText("o MS efetua o pagamento para as farmácias credenciadas a cada 60 dias e após o processamento das autorizações de Dispensação de Medicamentos e Correlatos (ADM), validadas no mês de referência.");
            c.setText("no PFPB, medicamentos para o tratamento de hipertensão arterial e diabetes mellitus são disponibilizados aos usuários por um valor equivalente a 10% do valor de venda.");
            d.setText("é constituído por convênio firmado com a rede privada de farmácias e drogarias.");
            e.setText("as prescrições médicas apresentadas devem ter validade máxima de 60 dias a partir de sua emissão, exceto para contraceptivos, cuja validade é de 6 meses.");
            alt = R.id.a;
        }
        if (question == 25) {
            questaoTextView.setText("De acordo com a Lei no. 8.080/1990, a Saúde é um direito fundamental do ser humano, devendo o Estado prover as condições indispensáveis ao seu pleno exercício. Por esse motivo, estão incluídas no campo de atuação do Sistema Único de Saúde (SUS)");
            a.setText("exclusivamente ações de vigilância sanitária.");
            b.setText("exclusivamente ações de vigilância sanitária e epidemiológica.");
            c.setText("exclusivamente ações de vigilância sanitária e epidemiológica, além de saúde do trabalhador.");
            d.setText("exclusivamente ações de vigilância sanitária e epidemiológica, saúde do trabalhador, além de assistência terapêutica integral, inclusive farmacêutica.");
            e.setText("ações de vigilância sanitária, nutricional e epidemiológica, saúde do trabalhador, assistência terapêutica integral, inclusive farmacêutica, formulação de políticas de saneamento básico, proteção ao meio ambiente, entre outros.");
            alt = R.id.e;
        }
        if (question == 26) {
            questaoTextView.setText("A portaria no. 802/1998 trata sobre as boas práticas de distribuição de produtos farmacêuticos. De acordo com essa portaria, assinale a alternativa correta.");
            a.setText("Distribuidores devem contar com profissional farmacêutico somente quando realizarem operações de fracionamento de embalagens originais ou primárias.");
            b.setText("Os medicamentos com embalagem violada ou suspeitos de qualquer contaminação devem ser retirados dos estoques comercializáveis, identificados e segregados em área totalmente separada de forma a não serem vendidos por engano, nem contaminarem outras mercadorias.");
            c.setText("Produtos que tenham sido devolvidos ao distribuidor não podem, em hipótese alguma, regressar aos estoques comercializáveis, pois não haverá mais a garantia da qualidade.");
            d.setText("Produtos com prazo de validade vencido deverão ser devolvidos para a própria distribuidora, onde serão destruídos, a fim de evitar a comercialização, o uso indevido e o risco a eles associado.");
            e.setText("Caso sejam identificados produtos farmacêuticos adulterados, falsificados ou com suspeita de falsificação na rede de distribuição, o distribuidor deve notificar imediatamente a unidade produtora, a fim de que o lote seja recolhido e destruído pela própria distribuidora.");
            alt = R.id.b;
        }
        if (question == 27) {
            questaoTextView.setText("No ciclo da assistência farmacêutica hospitalar, a aquisição de medicamentos é uma atividade de extrema importância, visto que este é um insumo fundamental de suporte às ações de saúde. Nesse sentido, uma boa aquisição de medicamentos deve considerar alguns aspectos, exceto um. Assinale a alternativa que o apresenta.");
            a.setText("O que comprar.");
            b.setText("Quando comprar.");
            c.setText("Quanto comprar.");
            d.setText("A irrelevância do medicamento junto a estudos farmacoepidemiológicos.");
            e.setText("A programação e a rotina da farmácia hospitalar.");
            alt = R.id.d;
        }
        if (question == 28) {
            questaoTextView.setText("Ao calcular previsão da demanda, na qual se atribui“pesos” diferenciados em relação a cada período de aquisição, segundo a fórmula Consumo médio (CM) = (C1x0,2) + (C2x0,3) .../n, tem-se a previsão baseada no(a)");
            a.setText("média aritmética.");
            b.setText("média ponderada.");
            c.setText("desvio padrão.");
            d.setText("média harmônica.");
            e.setText("média simples.");
            alt = R.id.b;
        }
        if (question == 29) {
            questaoTextView.setText("Considere a RDC nº 80/2006 e, em seguida, leia o texto abaixo.\n“Acondicionamento aprovado para fins de registro pelo órgão competente do Ministério da Saúde, destinado à proteção e à manutenção das características de qualidade, segurança e eficácia do produto.”\nÉ correto afirmar que o texto acima refere-se ao(à)");
            a.setText("sistema de dispensação.");
            b.setText("embalagem original.");
            c.setText("embalagem primária fracionada.");
            d.setText("embalagem secundária.");
            e.setText("estocagem.");
            alt = R.id.b;
        }
        if (question == 30) {
            questaoTextView.setText("Segundo a RDC nº 67/2007, é correto afirmar que, segundo os grupos de atividades desenvolvidas pelas farmácias, a manipulação de produtos estéreis pertence ao grupo");
            a.setText("II.");
            b.setText("III.");
            c.setText("IV.");
            d.setText("V.");
            e.setText("VI.");
            alt = R.id.c;
        }
        if (question == 31) {
            questaoTextView.setText("No exercício da atividade de quimioterapia nos estabelecimentos de saúde, caberá ao Farmacêutico\n I. selecionar, adquirir, armazenar e padronizar os componentes necessários ao preparo dos antineoplásicos.\n II. avaliar os componentes presentes na prescrição médica apenas quanto à quantidade, à qualidade e à compatibilidade.\n III. manipular drogas antineoplásicas em ambientes e condições assépticos, obedecendo a critérios internacionais de segurança.\n É correto o que está contido em");
            a.setText("I, II e III.");
            b.setText("I e II, apenas.");
            c.setText("I e III, apenas.");
            d.setText("III, apenas.");
            e.setText("II, apenas.");
            alt = R.id.c;
        }
        if (question == 32) {
            questaoTextView.setText("Entre as atribuições dos Estabelecimentos de Assistência à Saúde, que se desdobram em subatividades, estão, exceto:");
            a.setText("exclusividade de ações sociais e administrativas vinculadas à previdência social.");
            b.setText("atividades de ensino e pesquisa.");
            c.setText("atividades de apoio e diagnóstico laboratorial.");
            d.setText("atividades de apoio logístico de tratamento.");
            e.setText("atendimentos em regime ambulatorial.");
            alt = R.id.a;
        }
        if (question == 33) {
            questaoTextView.setText("A equipe multiprofissional de terapia nutricional é composta por alguns profissionais, não tendo obrigatoriedade legal a participação de");
            a.setText("médicos.");
            b.setText("farmacêuticos.");
            c.setText("nutricionistas.");
            d.setText("enfermeiros. ");
            e.setText("biomédicos. ");
            alt = R.id.e;
        }
        if (question == 34) {
            questaoTextView.setText("É incorreto afirmar que os produtos farmacêuticos e correlatos adquiridos industrialmente para o preparo de nutrição parenteral ");
            a.setText("devem ter registro no Ministério da Saúde. ");
            b.setText("independem de análise microbiológica. ");
            c.setText("necessitam de análise físico-química. ");
            d.setText("necessitam atender às especificações legais. ");
            e.setText("devem apresentar grau de pureza química. ");
            alt = R.id.b;
        }
        if (question == 35) {
            questaoTextView.setText("É correto afirmar que indicar e prescrever terapia nutricional, estabelecer o acesso intravenoso e participar do desenvolvimento técnico científico é papel preponderante do seguinte profissional: ");
            a.setText("enfermeiro. ");
            b.setText("farmacêutico. ");
            c.setText("nutricionista. ");
            d.setText("médico. ");
            e.setText("biomédico. ");
            alt = R.id.d;
        }
        if (question == 36) {
            questaoTextView.setText("De acordo com a Portaria nº 344/1998, é correto afirmar que substâncias que constam das listas de imunossupressoras devem ser classificadas como ");
            a.setText("A1. ");
            b.setText("A2. ");
            c.setText("D1. ");
            d.setText("C3. ");
            e.setText("B. ");
            alt = R.id.d;
        }
        if (question == 37) {
            questaoTextView.setText("Pertencem à lista de substâncias psicotrópicas, sujeitas à notificação de receitas, as seguintes substâncias: ");
            a.setText("Clobenzorex e Acetorfina. ");
            b.setText("Metanfetamina e Fenetilina. ");
            c.setText("Tramadol e Codeína. ");
            d.setText("Codeína e Nalorfina. ");
            e.setText("Propiram e Nalorfina. ");
            alt = R.id.b;
        }
        if (question == 38) {
            questaoTextView.setText("O documento de notificação de receitas de cor branca é aplicado a produtos, como ");
            a.setText("psicotrópicos de longa duração. ");
            b.setText("anorexígenos. ");
            c.setText("retinoides de uso sistêmico. ");
            d.setText("entorpecentes de uso geral. ");
            e.setText("entorpecentes de uso cirúrgico. ");
            alt = R.id.c;
        }
        if (question == 39) {
            questaoTextView.setText("Além de entorpecentes e psicotrópicos, é(são) regulado(s) pela Agência Nacional de Vigilância Sanitária (ANVISA):\n I. imunossupressores.\n II. precursores.\n III. anorexígenos. \n É correto o que está contido em ");
            a.setText("I, II e III. ");
            b.setText("I e III, apenas. ");
            c.setText("III, apenas. ");
            d.setText("II e III, apenas. ");
            e.setText("I, apenas. ");
            alt = R.id.a;
        }
        if (question == 40) {
            questaoTextView.setText("São substâncias antirretrovirais sujeitas ao receituário do programa HIV/AIDS, citadas na Portaria nº 344/1998, exceto: ");
            a.setText("Indinavir. ");
            b.setText("Zidovudina. ");
            c.setText("Nelfinavir. ");
            d.setText("Tretinoína. ");
            e.setText("Saquinavir. ");
            alt = R.id.d;
        }
        if (question == 41) {
            questaoTextView.setText("É correto afirmar que o uso de Captopril com Ácido Acetil Salicílico (AAS) tem como efeito clínico ");
            a.setText("o aumento da resposta anti-hipertensiva. ");
            b.setText("a diminuição da resposta anti-hipertensiva. ");
            c.setText("o aumento da hipercalemia. ");
            d.setText("os efeitos tóxicos nos néfrons. ");
            e.setText("a diminuição da hipercalemia. ");
            alt = R.id.b;
        }
        if (question == 42) {
            questaoTextView.setText("A principal ferramenta da farmacovigilância é a notificação, cujas informações visam à\n I. identificação precoce de reações adversas. \n II. identificação de fatores de risco e possíveis mecanismos subjacentes às reações adversas. \n III. avaliação e à comunicação dos riscos e dos benefícios dos medicamentos no mercado. \n IV. relação das aplicações epidemiológicas com os valores de mercado. \n É correto o que está contido em ");
            a.setText("I e II, apenas. ");
            b.setText("I, III e IV, apenas. ");
            c.setText("II, apenas. ");
            d.setText("I, II e III, apenas. ");
            e.setText("I e IV, apenas. ");
            alt = R.id.d;
        }
        if (question == 43) {
            questaoTextView.setText("Trata-se de informação notificada sobre possível relação causal entre um evento adverso e um medicamento, sendo que tal relação é desconhecida ou foi documentada, previamente, de forma incompleta. É correto afirmar que a descrição refere-se ao que se entende, em farmacovigilância, como ");
            a.setText("sinal. ");
            b.setText("reação adversa. ");
            c.setText("desvio de qualidade. ");
            d.setText("notificação epidemiológica. ");
            e.setText("vigilância em saúde. ");
            alt = R.id.a;
        }
        if (question == 44) {
            questaoTextView.setText("Leia o texto abaixo e, em seguida, assinale a alternativa que preenche corretamente a lacuna.\n O uso concomitante de digoxina com ____________ implica bradicardia, apresentando um grau de interação moderada. ");
            a.setText("ciclosporina ");
            b.setText("atenolol ");
            c.setText("propranolol ");
            d.setText("diazepam ");
            e.setText("clonazepam ");
            alt = R.id.a;
        }
        if (question == 45) {
            questaoTextView.setText("Trata-se de um medicamento que interage com sulfato ferroso, causando queda da absorção de ferro. É correto afirmar que a descrição refere-se ao(à) ");
            a.setText("omeprazol. ");
            b.setText("paracetamol. ");
            c.setText("piridoxina. ");
            d.setText("levodopa. ");
            e.setText("metotrexato. ");
            alt = R.id.a;
        }
        if (question == 46) {
            questaoTextView.setText("Assinale a alternativa que apresenta os agentes classificados como antinematoides que pertencem ao RENAME. ");
            a.setText("Nistatina. ");
            b.setText("Miconazol. ");
            c.setText("Albendazol. ");
            d.setText("Gentamicina. ");
            e.setText("Cetoconazol. ");
            alt = R.id.c;
        }
        if (question == 47) {
            questaoTextView.setText("Segundo os processos de reorientação da assistência farmacêutica junto ao Sistema Único de Saúde (SUS), assinale a alternativa incorreta. ");
            a.setText("Deve ser fundamentada na descentralização do sistema. ");
            b.setText("Deve ser fundamentada na promoção do uso racional de medicamentos. ");
            c.setText("Deve ser fundamentada na otimização de distribuição. ");
            d.setText("Deve priorizar o acesso da população ao uso correto. ");
            e.setText("Deve ser baseada na política de preços da indústria farmacêutica nacional. ");
            alt = R.id.e;
        }
        if (question == 48) {
            questaoTextView.setText("A assistência farmacêutica no SUS engloba as atividades de\n I. seleção. \n II. programação. \n III. aquisição. \n IV. armazenamento. \n É correto o que está contido em ");
            a.setText("I e III, apenas. ");
            b.setText("IV, apenas. ");
            c.setText("I, II ,III e IV. ");
            d.setText("III, apenas. ");
            e.setText("I, apenas. ");
            alt = R.id.c;
        }
        if (question == 49) {
            questaoTextView.setText("É(são) papel(is) do Farmacêutico junto ao controle das infecções hospitalares:\n I. participar das reuniões da Comissão de Controle de Infecção Hospitalar. \n II. participar da elaboração de protocolos de tratamentos com antimicrobianos. \n III. prescrever antimicrobianos em todos os âmbitos. \n É correto o que está contido em ");
            a.setText("I, II e III. ");
            b.setText("I e III, apenas. ");
            c.setText("I e II, apenas. ");
            d.setText("III, apenas. ");
            e.setText("II, apenas. ");
            alt = R.id.c;
        }
        if (question == 50) {
            questaoTextView.setText("Em relação à absorção de fármacos no intestino, é correto afirmar que ");
            a.setText("tipicamente, cerca de 75% de um fármaco administrado oralmente são absorvidos no período de 1 a 3 horas, mas diversos fatores, fisiológicos, ou relacionados com a formulação, podem alterar a absorção. ");
            b.setText("ácidos fortes, com pKa de 3 ou menos são bem absorvidos, porque estão totalmente ionizados. ");
            c.setText("o tamanho da partícula e o tipo de formulação pouco ou nada interferem na absorção. ");
            d.setText("bases fortes, com pKa de 10 ou mais são bem absorvidas, porque estão totalmente ionizadas. ");
            e.setText("existem alguns casos em que a absorção intestinal não depende de transportadores específicos, o que ocorre no caso do ferro. ");
            alt = R.id.a;
        }
        if (question == 51) {
            questaoTextView.setText("A depuração total de um fármaco ");
            a.setText("é equivalente à depuração renal do mesmo fármaco, independentemente de ser eliminado por outras vias ou mecanismos. ");
            b.setText("pode ser determinada, para um indivíduo, pela média da concentração plasmática do fármaco (por exemplo, em mg/L) no estado de equilíbrio. ");
            c.setText("é definida como o volume de plasma que contém a quantidade total do fármaco que foi removido do organismo por todas as vias, em uma dada unidade de tempo. ");
            d.setText("em um indivíduo é sempre a mesma, independentemente da dose usada. ");
            e.setText("não pode ser determinada pela medida da concentração plasmática do mesmo fármaco. ");
            alt = R.id.c;
        }
        if (question == 52) {
            questaoTextView.setText("Assinale a alternativa correta em relação aos fármacos que agem sobre o Sistema Nervoso Autônomo (SNA). ");
            a.setText("A atropina, agonista muscarínico, produz efeitos sedativos sobre o Sistema Nervoso Central (SNC), enquanto a escopolamina, que também é um agonista muscarínico, produz efeitos excitatórios sobre o SNC. ");
            b.setText("Os efeitos dos fármacos anticolinesterásicos, como a nicotina, incluem taquicardia, hipertensão, excesso de secreções e broncoconstrição. ");
            c.setText("Os principais efeitos dos antagonistas muscarínicos são a bradicardia e a vasodilatação, a contração da musculatura lisa das vísceras e a constrição da pupila. ");
            d.setText("O propranolol, um agonista da transmissão adrenérgica, é usado para produzir broncoconstrição e diminuir a taxa glicêmica. ");
            e.setText("A toxina botulínica bloqueia a liberação de acetilcolina em nível pré-sináptico. ");
            alt = R.id.e;
        }
        if (question == 53) {
            questaoTextView.setText("Os benzodiazepínicos ");
            a.setText("diminuem o tempo que a pessoa leva até dormir e aumentam a duração total do sono. ");
            b.setText("não são mais usados no tratamento da epilepsia, principalmente em crianças. ");
            c.setText("ligam-se diretamente aos receptores GABA que medeiam a transmissão sináptica inibitória em todo SNC. ");
            d.setText("são usados, principalmente, para tratar estados crônicos de ansiedade. ");
            e.setText("são muito usados para o tratamento a longo prazo da insônia, pois dificilmente ocorre desenvolvimento de tolerância. ");
            alt = R.id.a;
        }
        if (question == 54) {
            questaoTextView.setText("Em relação às drogas antiepilépticas, pode-se afirmar que ");
            a.setText("várias agem por afetar a excitabilidade da membrana por ação sobre os canais de sódio independentes de voltagem. ");
            b.setText("a carbamazepina, um dos antiepilépticos mais amplamente usados, é quimicamente derivada dos antidepressivos tricíclicos, e suas ações assemelham-se às da fenitoína. ");
            c.setText("algumas agem potencializando a ativação sináptica mediada pelo GABA. ");
            d.setText("a fenitoína tem aspectos farmacocinéticos muito peculiares: é pouco absorvida por via oral e cerca de 80 – 90% do conteúdo plasmático encontram-se livres de ligação a proteínas. ");
            e.setText("o valproato leva a uma diminuição significativa do conteúdo de GABA do cérebro e é um inibidor fraco das enzimas que inativam o GABA. ");
            alt = R.id.b;
        }
        if (question == 55) {
            questaoTextView.setText("Entre os antidepressivos que agem como inibidores seletivos da captação de serotonina, podemos citar: ");
            a.setText("bupropiona, sertralina, desipramina e erva de São João. ");
            b.setText("fenelzina, maprotilina, fluoxetina e nortriptilina. ");
            c.setText("imipramina, bupropiona, citalopram e duloxetina. ");
            d.setText("fluoxetina, paroxetina, escitalopram e fluvoxamina. ");
            e.setText("imipramina, amitriptilina, isocarboxazida, citalopram. ");
            alt = R.id.d;
        }
        if (question == 56) {
            questaoTextView.setText("O lítio é muito usado para controlar as oscilações de humor, características da doença maníaco-depressiva, e apresenta efeitos bioquímicos complexos.\n Assinale a alternativa que contém o efeito bioquímico que se acredita ser relevante para sua ação terapêutica. ");
            a.setText("Ativação da produção de APMc induzida por hormônios, ativando, consequentemente, outras respostas celulares. ");
            b.setText("Ativação das isoformas de glicogênio sintetase quinase 3 (GSK3) que fosforilam algumas enzimas envolvidas nas vias que levam à apoptose. ");
            c.setText("Permeação dos canais de Na+ controlados por voltagem, aumentando o K+ intracelular e a despolarização da célula. ");
            d.setText("Inibição da inositol monofosfatase que bloqueia a via fosfatidilinositol (PI), resultando na depleção de PI, bloqueando, assim, muitos efeitos mediados pelos receptores. ");
            e.setText("Ação antagonista do receptor GRII cortisólico, análogo do fator de inibição de melanócitos (MIF-1) e ação agonista dos receptores M1/M2 de melatonina. ");
            alt = R.id.d;
        }
        if (question == 57) {
            questaoTextView.setText("Em relação às drogas conhecidas como ‘AINES’ (anti-inflamatórios não esteroidais) e seus sítios de ligação nos tecidos, pode-se afirmar que ");
            a.setText("a maioria dos AINES tradicionais inibem tanto a COX-1 como a COX-2, embora variem no grau que inibe cada isoforma. ");
            b.setText("a COX-1 é induzida nas células inflamatórias, quando sofrem lesão ou inflamação, ou quando são ativadas por citocinas inflamatórias. ");
            c.setText("a COX-2 é uma enzima constitutiva, expressa na maioria dos tecidos e desempenha funções de “manutenção” e homeostase no organismo. ");
            d.setText("os AINES apresentam, também, efeitos diretos sobre a migração de leucócitos, liberação de enzimas lisossômicas e produção de radicais tóxicos de oxigênio, que contribuem para o dano tecidual de condições inflamatórias crônicas, como a artrite reumatoide. ");
            e.setText("todas têm o mesmo mecanismo de ação: são inibidoras da enzima ciclo-oxigenase (COX), que age sobre o ácido araquidônico das membranas celulares, aumentando a geração de PG e Pciclinas. ");
            alt = R.id.a;
        }
        if (question == 58) {
            questaoTextView.setText("Assinale a alternativa que contém, apenas, ações dos glicocorticoides sobre as células inflamatórias e sobre os mediadores das respostas inflamatórias. ");
            a.setText("Ativação da transcrição gênica de citocinas, aceleração da ‘troca’ da resposta imune do tipo Th1 para Th2, redução da atividade de osteoclastos, redução da liberação de histamina pelos basófilos. ");
            b.setText("Redução da atividade de LB, redução da transcrição gênica para produção de TNFα, ativação da função dos fibroblastos e consequente diminuição da cicatrização, redução da produção de IL-6 e IL-8. ");
            c.setText("Redução da saída de neutrófilos dos vasos sanguíneos, redução da ativação das células T-helper, redução da produção de óxido nítrico induzido, inibição da transcrição gênica da IL-1 e IL-2. ");
            d.setText("Redução da saída de neutrófilos dos vasos sanguíneos, ativação da expressão da COX-2, redução da síntese de IL-10, ativação da produção de óxido nítrico induzido. ");
            e.setText("Ativação das células T-helper, ativação dos fibroblastos, ativação da produção de IL-10, ativação da síntese de IL-1 e IL-4. ");
            alt = R.id.c;
        }
        if (question == 59) {
            questaoTextView.setText("Em relação aos efeitos colaterais causados pelos anti-hipertensivos mais usados, pode-se afirmar que os ");
            a.setText("antagonistas β-adrenérgicos, como o metoprolol, podem produzir hipotensão na primeira dose e disfunção renal reversível. ");
            b.setText("inibidores da ECA (enzima conversora da angiotensina), como o captopril, podem produzir tosse e teratogenicidade. ");
            c.setText("antagonistas da angiotensina 1, como as sartanas, podem produzir intolerância à glicose, hipocalemia e hiponatremia. ");
            d.setText("diuréticos tiazídicos, como a bendroflumetiazida, podem produzir broncoespasmo e bradicardia. ");
            e.setText("agonistas de Ca-2+, como o nifedipino, podem produzir gota, cansaço e mãos/pés frios. ");
            alt = R.id.b;
        }
        if (question == 60) {
            questaoTextView.setText("Assinale a alternativa correta em relação aos antiarrítmicos citados a seguir e seus mecanismos de ação. ");
            a.setText("A amiodarona prolonga substancialmente o potencial de ação cardíaca, mas apresenta graves efeitos colaterais. ");
            b.setText("A procainamida bloqueia os canais de sódio e é usada para prevenir a recorrência de taquicardia supraventricular paroxística. ");
            c.setText("A lidocaína impede a recorrência de taquiarritmias provocadas por aumento da atividade simpática. ");
            d.setText("Os glicosídeos cardíacos reduzem a frequência cardíaca e a velocidade de condução no AV, além de aumentar a força da contração, pois possuem efeito inotrópico positivo. ");
            e.setText("O verapamil ativa os canais de cálcio e é usado para prevenir arritmias ventriculares. ");
            alt = R.id.d;
        }
        if (question == 61) {
            questaoTextView.setText("Em relação aos fármacos usados para inibir ou neutralizar a secreção de ácido gástrico, pode-se afirmar que ");
            a.setText("o omeprazol administrado por via oral é absorvido e, do sangue, entra nas células parietais do estômago e, depois, nos canalículos, onde se acumula e inibe a H+-K+-ATPase de forma irreversível. ");
            b.setText("a cimetidina é um antagonista do receptor H-1 da histamina, que inibe a secreção de ácido gástrico basal, mas não a estimulada pela ingestão de alimentos. ");
            c.setText("a maioria dos antiácidos de uso comum são sais de alumínio e/ou magnésio, mas os sais de alumínio causam diarreia e os de magnésio, constipação. ");
            d.setText("o hidróxido de alumínio eleva consideravelmente o pH do suco gástrico e também adsorve a pepsina, mas seu efeito é efêmero, com duração de até 30 minutos. ");
            e.setText("os alginatos são, algumas vezes, combinados aos antiácidos, pois diminuem a viscosidade e a aderência do muco à mucosa esofágica, formando uma barreira protetora. ");
            alt = R.id.a;
        }
        if (question == 62) {
            questaoTextView.setText("O mebendazol é um anti-helmíntico de amplo espectro pertencente ao grupo dos benzimidazólicos, e muito usado clinicamente. Entre os seus alvos, podem-se citar: ");
            a.setText("Strongyloides stercoralis, Trichinella spiralis, Taenia solium. ");
            b.setText("Enterobius vermicularis, Ascaris lumbricoides, Ancylostoma duodenale. ");
            c.setText("Schistosoma mansoni, Ascaris lumbricoides, Taenia saginata. ");
            d.setText("Wuchereria bancrofti, Ancylostoma duodenale, Schistosoma mansoni. ");
            e.setText("Enterobius vermicularis, Trichinella spiralis, Wuchereria bancrofti. ");
            alt = R.id.b;
        }
        if (question == 63) {
            questaoTextView.setText("São todos antibióticos β-lactâmicos: ");
            a.setText("penicilinas, cloranfenicol, carbapenens, vancomicina. ");
            b.setText("cefalosporinas, eritromicina, vancomicina, quinolonas. ");
            c.setText("eritromicina, tetraciclinas, polimixinas, estreptograminas. ");
            d.setText("penicilinas, cefalosporinas, carbapenens, cefamicinas. ");
            e.setText("cloranfenicol, tetraciclinas, cefalosporinas, eritromicina. ");
            alt = R.id.d;
        }
        if (question == 64) {
            questaoTextView.setText("Assinale a alternativa correta em relação às drogas antibacterianas. ");
            a.setText("A eritromicina é uma droga bactericida que age inibindo a síntese de parede. ");
            b.setText("Os aminoglicosídeos agem inibindo a síntese de parede das bactérias e são facilmente absorvidos pelo trato gastrintestinal. ");
            c.setText("A cefotaxima é uma droga semissintética de amplo espectro. ");
            d.setText("O cloranfenicol possui propriedades detergentes e exerce sua função alterando a membrana celular externa. ");
            e.setText("As penicilinas agem interferindo na síntese dos lipídeos que formam as membranas interna e externa das bactérias. ");
            alt = R.id.c;
        }
        if (question == 65) {
            questaoTextView.setText("A interação farmacodinâmica entre fármacos pode ocorrer de muitas maneiras diferentes, de difícil classificação. Entre elas, pode(m)-se citar: ");
            a.setText("as sulfonamidas impedem a síntese de ácido fólico pelas bactérias e outros micro-organismos; a trimetoprima inibe a redução do ácido fólico à tetra-hidrofolato. Administrados juntos, os fármacos têm ação sinérgica importante no tratamento de algumas infecções. ");
            b.setText("alguns agonistas de receptores β-adrenérgicos diminuem a efetividade de outros agonistas β-adrenérgicos, como o salbutamol. ");
            c.setText("a prometazina, antagonista do receptor H-1 da histamina, se administrada a pacientes que recebem tratamento para hipertensão, causa aumento da pressão sanguínea. ");
            d.setText("a digoxina inibe a isoforma da fosfodiesterase tipo V, que inativa o GMPc; consequentemente potencializa os nitratos orgânicos que ativam a guanililciclase e pode causar hipotensão grave em pacientes que usarem esses dois fármacos. ");
            e.setText("o AAS compete com a vitamina K, impedindo a síntese hepática de vários fatores de coagulação; o risco de sangramento é aumentado por fármacos como o ibuprofeno, que inibe a síntese de prostaglandinas vasodilatadoras. ");
            alt = R.id.a;
        }
        if (question == 66) {
            questaoTextView.setText("Os lipídeos, incluindo o colesterol e os triglicérides, são transportados no plasma na forma de lipoproteínas. Assinale a alternativa que contém a informação correta sobre as lipoproteínas. ");
            a.setText("Os quilomícrons, que são as partículas menores e menos densas, transportam triglicérides, mas não colesterol, do trato gastrintestinal para os tecidos, onde são clivados pela lipoproteína lipase, liberando ácidos graxos livres e glicerol. ");
            b.setText("As HDL, ricas em ésteres de colesterol, são a principal forma de distribuição de colesterol aos vários tecidos, que é necessário para síntese de membranas e hormônios. As HDL são captadas pelas células mediante receptores de membrana especiais, que a célula produz na medida de sua necessidade de importar colesterol. ");
            c.setText("As LDL têm a menor proporção de proteína. São sintetizadas no fígado para exportação de triglicérides para os tecidos, especialmente o adiposo. ");
            d.setText("As VLDL originam-se basicamente do fígado e intestino na forma de bicamadas discoides de fosfolípides. No plasma, captam colesterol não esterificado e o incorporam em seu centro hidrofóbico, entregando-o aos hepatócitos para catabolismo. Agem, portanto, como “lixeiros” de colesterol. ");
            e.setText("Uma partícula lipoproteica é composta por um centro contendo lipídeos apolares (triglicérides e ésteres de colesterol) e uma membrana de fosfolípides que constitui o limite externo da partícula e sua interface com o plasma onde estão inseridas as várias apoproteínas. ");
            alt = R.id.e;
        }
        if (question == 67) {
            questaoTextView.setText("A osmolaridade calculada de uma mistura intravenosa que contém 0,9% de cloreto de sódio e 5% de dextrose é:\n Dados: massa molar do cloreto de sódio = 58,5; \n massa molar da dextrose = 180. ");
            a.setText("277,78 mOsmol/ L. ");
            b.setText("585,47 mOsmol/ L. ");
            c.setText("58,50 mOsmol/ L. ");
            d.setText("238,50 mOsmol/ L. ");
            e.setText("307,69 mOsmol/ L. ");
            alt = R.id.b;
        }
        if (question == 68) {
            questaoTextView.setText("A concentração de insulina em ppm, de uma solução que contém 10 μg em 10 mL, é: ");
            a.setText("0,1. ");
            b.setText("1,0. ");
            c.setText("100,0. ");
            d.setText("10,0. ");
            e.setText("0,01. ");
            alt = R.id.b;
        }
        if (question == 69) {
            questaoTextView.setText("Assinale a alternativa correta sobre as cápsulas de gelatina dura. ");
            a.setText("Depois de seca, a gelatina fica instável na presença do ar. ");
            b.setText("Em ambientes extremamente secos, as cápsulas podem ficar mais sujeitas à decomposição microbiana. ");
            c.setText("As cápsulas dessa gelatina contêm cerca de 25 a 30% de umidade. ");
            d.setText("A gelatina é insolúvel no fluido gástrico, de modo que as cápsulas preparadas com esse material são destinadas à produção de medicamentos de liberação entérica. ");
            e.setText("Quando armazenadas em ambiente úmido, elas absorvem a umidade adicional e podem se tornar distorcidas e perder a forma rígida. ");
            alt = R.id.e;
        }
        if (question == 70) {
            questaoTextView.setText("A água utilizada como veículo ou solvente para injetáveis e diluente para soluções parenterais deve atender a uma série de requisitos de pureza e esterilidade.\n Sobre esses requisitos, é correto afirmar que ");
            a.setText("a concentração de endotoxinas não deve exceder 0,25 UI/ mL. ");
            b.setText("a condutividade deve ser de 0,055 a 0,1 mS/cm a 25,0 ºC ± 0,5 ºC. ");
            c.setText("o carbono orgânico total (COT) deve ser menor que 0,05 mg/ L. ");
            d.setText("a contagem total de bactérias deve ser menor que 100 UFC/ mL. ");
            e.setText("a contagem total de micro-organismos mesófilos deve ser menor que 1 UFC/100mL. ");
            alt = R.id.a;
        }
        if (question == 71) {
            questaoTextView.setText("Na administração de um fármaco, pode-se prever que o ideal seria administrá-lo diretamente no local onde a ação é desejável, o que aumentaria a eficácia terapêutica e tenderia a reduzir os efeitos indesejáveis. No entanto, por vários fatores, nem sempre isso é possível e deve-se recorrer a outras vis de administração que apresentam vantagens e desvantagens.\n Assinale a alternativa correta sobre as vantagens e desvantagens de cada uma das vias de administração de medicamentos. ");
            a.setText("Uma vantagem da administração oral consiste no fato de os fármacos utilizados por essa via sofrerem o efeito de primeira passagem pelo fígado. ");
            b.setText("A administração cutânea é indicada apenas quando se deseja ação local, visto que não oferece o acesso dos fármacos à circulação sistêmica. ");
            c.setText("A administração cutânea apresenta menor risco de superdosagem que a administração intravenosa. ");
            d.setText("Medicamentos administrados por via intramuscular não sofrem os efeitos da passagem pela circulação pulmonar e vão diretamente aos tecidos. ");
            e.setText("A administração intramuscular somente é indicada para fármacos de liberação prolongada. ");
            alt = R.id.c;
        }
        if (question == 72) {
            questaoTextView.setText("Assinale a alternativa correta sobre os conceitos de biodisponibilidade e bioequivalência. ");
            a.setText("A biodisponibilidade depende principalmente da metabolização pré-hepática de um fármaco, de modo que possa chegar ao sítio de ação em sua forma ativa. ");
            b.setText("A bioequivalência é o estudo comparativo de produtos contendo princípios ativos diferentes para a mesma finalidade terapêutica e administrados pela mesma via. ");
            c.setText("A biodisponibilidade das diferentes formas farmacêuticas pode ser avaliada in vivo pela realização de testes de dissolução e de permeação. ");
            d.setText("Dois produtos são considerados bioequivalentes quando, ao serem administrados nas mesmas condições experimentais, na mesma dose molar, não apresentam diferenças significativas em relação à quantidade do fármaco absorvida e a sua velocidade de absorção. ");
            e.setText("Os ensaios de biodisponibilidade devem ser realizados principalmente entre as diferentes formulações intravenosas de um mesmo fármaco para poder garantir a eficácia terapêutica. ");
            alt = R.id.d;
        }
        if (question == 73) {
            questaoTextView.setText("O financiamento da Assistência Farmacêutica (AF) é responsabilidade dos três gestores do SUS, mas cabe ao governo federal ");
            a.setText("uma parte variável do Componente Básico destinada ao custeio de ações relativas aos programas de controle de Endemias, Tuberculose, Hanseníase, Malária, Leishmaniose, Chagas, DST-AIDS e imunobiológicos. ");
            b.setText("uma parte fixa do Componente Básico, com valor per capita, transferido aos estados, municípios e DF, com contrapartida das esferas alocando recursos próprios de acordo com os valores pactuados entre as 3 esferas de gestão. ");
            c.setText("o Componente Estratégico da Assistência Farmacêutica, com base em valor per capita e destinado aos programas de Hipertensão, Diabetes, Asma e Rinite, Saúde mental e Saúde da Mulher. ");
            d.setText("o Componente de Medicamentos de Dispensação Excepcional destinado ao custeio exclusivo pela esfera federal para a aquisição de medicamentos de dispensação excepcional, de acordo com a tabela de procedimentos do SUS. ");
            e.setText("o Componente Básico da Assistência Farmacêutica é composto apenas por uma parte fixa, com base em um valor per capita e destinado ao custeio de ações e serviços inerentes à AF. ");
            alt = R.id.b;
        }
        if (question == 74) {
            questaoTextView.setText("Dentro do Ciclo da Assistência Farmacêutica, a seleção de medicamentos é a etapa mais importante, pois a partir dela são desenvolvidas todas as demais atividades. \nAssinale a alternativa em que a etapa do processo de seleção de medicamentos está definida corretamente. ");
            a.setText("5.ª etapa: fase política – apoio e sensibilização do gestor e dos profissionais de saúde. ");
            b.setText("4.ª etapa: seleção propriamente dita, cujo resultado é a relação de medicamentos essenciais que irá nortear as diretrizes, utilização, programação, aquisição etc. ");
            c.setText("2.ª etapa: fase técnico-normativa – criação de Comissão de Farmácia e Terapêutica em caráter permanente e deliberativo. ");
            d.setText("1.ª etapa: elaboração de um formulário terapêutico. ");
            e.setText("3.ª etapa: fase de divulgação e implementação a partir da elaboração de estratégias para validar e legitimar o processo. ");
            alt = R.id.c;
        }
        if (question == 75) {
            questaoTextView.setText("De acordo com a Lei n.º 9.787, de 10/2/99, o medicamento genérico é aquele “similar a um produto de referência ou inovador, que se pretende ser com este intercambiável, geralmente produzido após a expiração ou renúncia da proteção patentária ou de outros direitos de exclusividade, comprovada a sua eficácia, segurança e qualidade, e designado pela DCB ou, na sua ausência, pela DCI”. No entanto, nem todos os medicamentos podem ser registrados como genéricos. Assinale aquele que, dentro das condições exigidas pela Lei, pode ser registrado como medicamento genérico. ");
            a.setText("Insulinas. ");
            b.setText("Antissépticos de uso hospitalar. ");
            c.setText("Fitoterápicos. ");
            d.setText("Contrastes radiológicos. ");
            e.setText("Anti-inflamatórios não-esteroides de uso tópico. ");
            alt = R.id.e;
        }
        if (question == 76) {
            questaoTextView.setText("De acordo com a Portaria n.º 344/1998, a Autorização Especial é um documento concedido pela Secretaria de Vigilância Sanitária do Ministério da Saúde após petição, que concede ao estabelecimento a autorização para a realização de atividades com as substâncias, bem como os medicamentos que as contenham constantes nas listas anexas do mesmo Regulamento Técnico. Essa autorização não é necessária para as empresas que realizam ");
            a.setText("manipulação, por farmácias magistrais. ");
            b.setText("distribuição para comércio atacadista. ");
            c.setText("embalagem e reembalagem. ");
            d.setText("drogarias e Unidades de Saúde que apenas façam a dispensação em suas embalagens originais. ");
            e.setText("plantio, cultivo e colheita de plantas das quais possam ser extraídas essas substâncias. ");
            alt = R.id.d;
        }
        if (question == 77) {
            questaoTextView.setText("Sobre a estocagem de medicamentos sujeitos a controle especial, é correto afirmar que o estoque ");
            a.setText("do medicamento talidomida não pode ser superior à quantidade prevista para 3 meses de consumo. ");
            b.setText("das substâncias e medicamentos das listas B1 e B2 (psicotrópicos) não pode ser superior à quantidade prevista para 1 ano de consumo. ");
            c.setText("das substâncias e medicamentos das listas A1 e A2 (entorpecentes) não pode ser superior à quantidade prevista para 3 meses de consumo. ");
            d.setText("de medicamentos destinados aos Programas Especiais do Sistema Único de Saúde não está sujeito às exigências de quantidade de estoque. ");
            e.setText("das substâncias e medicamentos da lista C3 (imunossupressoras) não pode ser superior à quantidade prevista para 6 meses de consumo. ");
            alt = R.id.d;
        }
        if (question == 78) {
            questaoTextView.setText("O controle e o monitoramento ambiental da área de estocagem de medicamentos são essenciais para assegurar a estabilidade e a integridade dos mesmos até o momento de sua utilização.\n Assinale a alternativa correta sobre os equipamentos e o mecanismo pelo qual podem ser garantidas as condições especiais de temperatura. ");
            a.setText("Deve ser mantido espaço suficiente entre os produtos armazenados, para permitir a circulação adequada de ar e a distribuição por igual da temperatura. ");
            b.setText("Em regiões de clima quente, é necessário conservar a maior parte dos medicamentos em câmaras frias, com faixa de temperatura entre 2 a 8 ºC. ");
            c.setText("O refrigerador do tipo doméstico é adequado para a estocagem de medicamentos menos susceptíveis às variações de temperatura, como as vacinas. ");
            d.setText("Como regra geral, produtos sensíveis a temperaturas acima de 8 ºC devem ser estocados próximo da porta, enquanto que aqueles susceptíveis a temperaturas abaixo de 2 ºC devem ser alocados próximo ao fluxo de ar da unidade de resfriamento. ");
            e.setText("Quando em um produto houver a indicação de armazenamento entre 8 e 15 ºC e não houver câmara fria com esse ajuste de temperatura, esse produto pode ser armazenado em ambiente controlado com temperatura entre 23 a 25 ºC. ");
            alt = R.id.a;
        }
        if (question == 79) {
            questaoTextView.setText("Sobre o fracionamento de medicamentos, que pode ser realizado em farmácias e drogarias, a partir de embalagens desenvolvidas para essa finalidade e com o objetivo de dispensar quantidades individualizadas para atender às necessidades terapêuticas dos usuários desses produtos, é correto afirmar que ");
            a.setText("pode ser realizado com medicamentos sujeitos a controle especial, desde que a embalagem primária seja destinada ao fracionamento. ");
            b.setText("quando da insuficiência de um medicamento fracionável para completar a quantidade determinada no receituário, o farmacêutico poderá completar com outro medicamento bioequivalente, atendendo ao princípio de intercambialidade. ");
            c.setText("os medicamentos isentos de prescrição, destinados ao fracionamento, poderão permanecer ao alcance dos consumidores e usuários de medicamentos. ");
            d.setText("o fracionamento e a dispensação somente poderão ser realizados sob prescrição médica, mesmo para aqueles medicamentos isentos de prescrição. ");
            e.setText("é vedada a substituição de medicamentos fracionáveis por medicamentos manipulados. ");
            alt = R.id.e;
        }
        if (question == 80) {
            questaoTextView.setText("Sobre a dispensação de medicamentos antimicrobianos, é correto afirmar que ");
            a.setText("a dispensação de antimicrobianos, em farmácias e drogarias públicas e privadas, dar-se-á mediante a retenção da 1.ª via da receita, devendo a 2.ª via ser devolvida ao paciente. ");
            b.setText("a receita de antimicrobianos poderá conter a prescrição conjunta de outros medicamentos sujeitos a controle especial. ");
            c.setText("em situações de tratamento prolongado, a receita poderá ser utilizada para aquisições dentro de um período de 90 dias, a contar da data de sua emissão. ");
            d.setText("a receita de antimicrobianos é válida em todo o território nacional por 30 dias, a partir da data de emissão. ");
            e.setText("somente pode ser realizada mediante apresentação de receituário de Controle Especial, devidamente preenchido e carimbado pelo médico prescritor. ");
            alt = R.id.c;
        }
        if (question == 81) {
            questaoTextView.setText("A Lei n.º 8.080/1990 dispõe que o conjunto de ações e serviços de saúde, prestados por órgãos e instituições públicas federais, estaduais e municipais, da administração direta e indireta e das fundações mantidas pelo Poder Público, constitui o Sistema Único de Saúde – SUS. Sobre a organização, direção e gestão do SUS, é correto afirmar que ");
            a.setText("serão criadas comissões intersetoriais de âmbito nacional, subordinadas ao Ministério da Saúde. ");
            b.setText("deverão ser criadas comissões permanentes de integração entre os serviços de saúde e as instituições de ensino profissional e superior. ");
            c.setText("as ações e serviços de saúde, executados pelo SUS não podem ser complementados com a participação da iniciativa privada. ");
            d.setText("a direção do SUS é única e em todas as esferas de ação deve ser exercida pelo Ministério da Saúde. ");
            e.setText("as ações e serviços de saúde executados pelo SUS serão organizados de forma centralizada e hierarquizada em níveis de complexidade descrescente. ");
            alt = R.id.b;
        }
        if (question == 82) {
            questaoTextView.setText("De acordo com a Lei n.º 8.142, de 28 de dezembro de 1990, é correto afirmar que ");
            a.setText("o Conselho de Saúde é um órgão colegiado composto apenas por representantes da comunidade de esferas não governamentais. ");
            b.setText("a Conferência de Saúde reunir-se-á a cada 5 anos com a representação de vários segmentos sociais para avaliar a situação da saúde e propor diretrizes para a formulação de políticas de saúde. ");
            c.setText("as Conferências de Saúde e os Conselhos de Saúde terão a sua organização e normas de funcionamento definidas em regimento próprio, aprovadas pelo Conselho Nacional de Saúde. ");
            d.setText("a participação da comunidade na gestão do SUS dar-se-á por meio de instâncias colegiadas como a Conferência de Saúde e o Conselho de Saúde. ");
            e.setText("para o recebimento de recursos destinados à cobertura de ações e serviços de saúde, estados e municípios não precisam ter um Conselho de Saúde. ");
            alt = R.id.d;
        }
        if (question == 83) {
            questaoTextView.setText("O cultivo de bactérias e fungos no laboratório de rotina de microbiologia de alimentos é realizado com meios de cultura específicos para cada finalidade. Centenas desses meios desidratados estão disponíveis comercialmente e o seu preparo é realizado seguindo as instruções dos fabricantes. Em relação ao preparo desses meios de cultura, assinale a alternativa correta. ");
            a.setText("Alguns ingredientes de meios de cultura, como peptonas e extrato de levedura, são degradados durante a autoclavação e não devem ser esterilizados por processos que utilizam temperaturas acima de 100 ◦C. ");
            b.setText("Os meios semissólidos contêm ágar na concentração de 1,0% a 1,2% ou de 1,0 g a 1,2 g por 100 mL, podendo ser distribuídos em placas de Petri ou tubos de ensaio não inclinados. ");
            c.setText("Meios complexos são meios formulados com ingredientes de composição química definida e com grau de pureza conhecida, que não podem ser esterilizados por autoclavação. ");
            d.setText("Meios de cultura líquidos e sólidos podem ser esterilizados em Erlenmeyer na estufa de esterilização desde que a temperatura não ultrapasse 121 ◦C. ");
            e.setText("Meios sólidos desidratados de identificação bioquímica devem ser preparados conforme instruções do fabricante e distribuídos nos tubos de ensaio antes da esterilização. ");
            alt = R.id.e;
        }
        if (question == 84) {
            questaoTextView.setText("Salmonella spp. é a principal causa de doenças de origem alimentar em várias partes do mundo, inclusive no Brasil.\n Em relação ao gênero Salmonella, assinale a alternativa correta. ");
            a.setText("A maioria dos sorovares produz sulfeto de hidrogênio (H2S), que é identificado nos meios de cultura através de indicadores de H2S, como o citrato férrico amoniacal. ");
            b.setText("O enriquecimento não seletivo para o isolamento em alimentos pode ser realizado empregando caldo selenito-cistina e caldo lactosado bile verde brilhante. ");
            c.setText("Os sorovares Enteritidis e Typhimurium podem ser diferenciados pelas provas bioquímicas de descarboxilação de lisina e ornitina, produção de indol e H2S, hidrólise da ureia e motilidade. ");
            d.setText("Pertence à família Enterobacteriaceae e, portanto, não fermenta a glicose, não reduz nitrato a nitrito e produz a enzima oxidase. ");
            e.setText("Produz butilenoglicol (Voges-Proskauer negativo) e fenilalanina deaminase. ");
            alt = R.id.a;
        }
        if (question == 85) {
            questaoTextView.setText("O método convencional de contagem de micro-organismos consiste no plaqueamento de alíquotas do alimento homogeneizado e de suas diluições em meios de cultura sólidos, adequadamente selecionados em função da análise microbiológica a ser realizada. A identificação de colônias isoladas dos meios sólidos pode ser realizada por provas bioquímicas.\n Em relação à análise utilizada para o isolamento e a identificação dos diferentes micro-organismos em alimentos, assinale a alternativa correta. ");
            a.setText("A temperatura ótima para a multiplicação da maioria dos fungos é de 35 ◦C a 37 ◦C, com a diminuição do crescimento na faixa de 25 ◦C a 28 ◦C. ");
            b.setText("Bacillus cereus fermenta manitol e o uso desse açúcar nos meios de isolamento facilita a identificação de colônias típicas dessa bactéria. ");
            c.setText("Staphylococcus aureus produz as enzimas catalase, coagulase e termonuclease, além de ter a habilidade de se multiplicar em meios com 7,5% de cloreto de sódio (NaCℓ). ");
            d.setText("O meio de ágar Vermelho Violeta Bile (VRB), contendo lactose, é utilizado para a contagem de enterobactérias por inibir bactérias gram-negativas e possibilitar a diferenciação de colônias típicas pela fermentação da lactose. ");
            e.setText("Uma das características utilizadas para o isolamento em alimentos de Clostridium perfringens é a sua capacidade de multiplicar em temperaturas na faixa de 2 ◦C a 8 ◦C. ");
            alt = R.id.c;
        }
        if (question == 86) {
            questaoTextView.setText("Várias análises utilizadas no laboratório de microbiologia de alimentos são qualitativas e são realizadas para saber se um determinado micro-organismo está ou não presente na amostra. Nesse tipo de análise, é utilizado o enriquecimento em um ou mais caldos específicos com posterior isolamento em meios sólidos. Em relação aos meios de enriquecimento e aos meios seletivos e diferenciais, assinale a alternativa correta. ");
            a.setText("Os agentes diferenciais mais utilizados em meios sólidos seletivos e diferenciais são os indicadores de pH, como fenolftaleína e azul de metileno. ");
            b.setText("Os meios de pré-enriquecimento são utilizados com o objetivo de inibir a microbiota competidora presente nas amostras e favorecer a multiplicação do micro-organismo alvo. ");
            c.setText("Os meios seletivos e diferenciais favorecem a multiplicação da microbiota competidora e a sua identificação. ");
            d.setText("O emprego de meios de enriquecimento seletivo tem por objetivo a reparação de células bacterianas patogênicas injuriadas, que podem estar presentes nos alimentos industrializados. ");
            e.setText("O plaqueamento em meios seletivos e diferenciais, a partir de caldo de enriquecimento seletivo, é realizado empregando a técnica de inoculação por esgotamento e estrias superficiais. ");
            alt = R.id.e;
        }
        if (question == 87) {
            questaoTextView.setText("Um plano estatístico adequado de amostragem deve ser seguido quando da avaliação de lotes de alimentos. Os mais utilizados na análise microbiológica são os planos de duas ou três classes, adotados pela Agência Nacional de Vigilância Sanitária, Ministério da Saúde, Brasil.\n Em relação a esses planos de amostragem, considere as afirmativas a seguir. \n I. O plano de duas classes classifica o lote em duas categorias, aceitável e inaceitável. Esse plano é recomendado para ensaios de presença/ausência de um determinado micro-organismo. \n II. O parâmetro c é o número máximo de unidades de amostra que pode ser aceito com contagens acima de M, mas não acima de m. \n III. O parâmetro M é o padrão microbiológico estabelecido pela legislação para um determinado alimento. \n O parâmetro m é o limite tolerável, acima do M, que pode ser alcançado por algumas unidades de amostra, mas não pode ser ultrapassado por nenhuma. \n IV. Amostra do lote é a fração do total produzido, retirada ao acaso, para avaliar as condições do lote.Unidade de amostra é a quantidade de alimento efetivamente utilizada na realização do ensaio. \n Assinale a alternativa correta. ");
            a.setText("Somente as afirmativas I e II são corretas. ");
            b.setText("Somente as afirmativas I e IV são corretas. ");
            c.setText("Somente as afirmativas III e IV são corretas. ");
            d.setText("Somente as afirmativas I, II e III são corretas. ");
            e.setText("Somente as afirmativas II, III e IV são corretas. ");
            alt = R.id.a;
        }
        if (question == 88) {
            questaoTextView.setText("O transporte das amostras de alimentos para a análise microbiológica, assim como o seu armazenamento até o início das análises, é fundamental para a exatidão dos resultados.\n Em relação ao armazenamento e ao transporte de amostras de alimentos para a análise microbiológica, considere as afirmativas a seguir. \n I. Amostras de água engarrafada devem ser transportadas à temperatura ambiente na embalagem original lacrada. Não há necessidade de refrigeração. \n II. Amostras de alimentos para a contagem de bactérias aeróbias psicrotróficas devem ser analisadas até 6 horas após a coleta. A estocagem refrigerada permite a multiplicação desse grupo de bactérias. \n III. Amostras de alimentos para a análise de Vibrio parahemolyticus, Clostridium perfringens e Campylobacter spp. devem ser transportadas congeladas para facilitar o isolamento. \n IV. Frascos para o transporte de água clorada devem conter bicarbonato de sódio para neutralizar o cloro residual e impedir a continuação do efeito bactericida sobre a microbiota presente. \n Assinale a alternativa correta. ");
            a.setText("Somente as afirmativas I e II são corretas. ");
            b.setText("Somente as afirmativas I e IV são corretas. ");
            c.setText("Somente as afirmativas III e IV são corretas. ");
            d.setText("Somente as afirmativas I, II e III são corretas. ");
            e.setText("Somente as afirmativas II, III e IV são corretas. ");
            alt = R.id.a;
        }
        if (question == 89) {
            questaoTextView.setText("O método de Kjeldahl é o método primário para determinação do conteúdo de proteínas em alimentos. A técnica é composta por três etapas principais: digestão ou mineralização da amostra, destilação e titulação. Na digestão, emprega-se uma mistura digestora contendo H2SO4 concentrado, CuSO4 e K2SO4. Assinale a alternativa que apresenta, corretamente, a função do K2SO4. ");
            a.setText("Aumentar o tempo da digestão da amostra. ");
            b.setText("Aumentar o ponto de ebulição da mistura. ");
            c.setText("Agente oxidante. ");
            d.setText("Agente redutor. ");
            e.setText("Catalizador da reação química. ");
            alt = R.id.b;
        }
        if (question == 90) {
            questaoTextView.setText("A cromatografia e a eletroforese são métodos muito empregados em análise de alimentos e têm como princípio a separação de compostos por migração diferencial.\n Em relação a essas técnicas, assinale a alternativa correta. ");
            a.setText("A eluição isocrática refere-se ao uso de uma composição de fase móvel alterada durante a eluição cromatográfica. ");
            b.setText("A poliacrilamida é uma fase estacionária quimicamente ligada ao suporte cromatográfico. ");
            c.setText("As proteínas podem ser separadas por cromatografia de troca iônica por possuírem cargas e apresentarem diferentes taxas de migração em um campo elétrico. ");
            d.setText("Na cromatografia em papel, a separação ocorre por partição do soluto entre dois líquidos. ");
            e.setText("O dodecil sulfato de sódio (SDS) é empregado na eletroforese de proteínas para hidrolisar ligações peptídicas, reduzir suas massas moleculares e melhorar suas mobilidades. ");
            alt = R.id.d;
        }
        if (question == 91) {
            questaoTextView.setText("Na análise de alimentos, as etapas de amostragem, preparo e conservação das amostras são de fundamental importância para a confiabilidade dos resultados.\n Sobre o armazenamento e a redução da amostra bruta para análise físico-química de alimentos, considere as afirmativas a seguir. \n I. Refrigerantes devem ser homogeneizados na própria embalagem e então retirar várias porções para análise imediata. \n II. Amostras de margarina devem ser aquecidas a 35 ◦C em frasco com tampa e, depois, agitadas para homogeneização e, a partir daí, são retiradas as alíquotas necessárias para análise. \n III. Sorvete deve ser deixado em repouso à temperatura ambiente, até se liquefazer, para depois ser homogeneizado e transferido para um frasco com rosca esmerilhada, que, então, será fechado e conservado sob refrigeração para a realização da análise. \n IV. Quando não for possível a análise nas amostras frescas a fim de diminuir as alterações oxidativas, recomenda-se o congelamento para a maioria dos alimentos. \n Assinale a alternativa correta. ");
            a.setText("Somente as afirmativas I e II são corretas. ");
            b.setText("Somente as afirmativas I e IV são corretas. ");
            c.setText("Somente as afirmativas III e IV são corretas. ");
            d.setText("Somente as afirmativas I, II e III são corretas. ");
            e.setText("Somente as afirmativas II, III e IV são corretas. ");
            alt = R.id.e;
        }
        if (question == 92) {
            questaoTextView.setText("Uma porção de 20 g de doce de coco cremoso contém 3,6 g de umidade, 13,0 g de carboidratos, 2,2 g de gorduras e 1,2 g de fibras.\n Assinale a alternativa que apresenta, corretamente, o conteúdo, em % (m/m), de gordura no doce, em base seca. ");
            a.setText("7,40 ");
            b.setText("9,02 ");
            c.setText("11,00 ");
            d.setText("13,41 ");
            e.setText("21,95 ");
            alt = R.id.d;
        }
        if (question == 93) {
            questaoTextView.setText("Para determinar o conteúdo de ácido acético (CH3COOH) em amostra de vinagre, uma alíquota de 10 mL do vinagre foi transferida para um balão volumétrico de 100 mL e o volume foi completado com água destilada. Uma amostra de 15 mL da solução diluída de vinagre foi titulada com 20 mL de uma solução de NaOH 0,1 mol/L, fator de correção 0,985, usando solução de fenolftaleína como indicador do ponto final da titulação.\n Assinale a alternativa que apresenta, corretamente, o conteúdo, em g/100 mL, de ácido acético no vinagre. \nDados: massas atômicas: H = 1 u; C = 12 u; O = 16 u; Na = 23 u ");
            a.setText("0,79 ");
            b.setText("0,80 ");
            c.setText("7,88 ");
            d.setText("8,00 ");
            e.setText("8,87 ");
            alt = R.id.c;
        }
        if (question == 94) {
            questaoTextView.setText("Quinze gramas de ácido ascórbico (H2C6H606) foram dissolvidos em 150 mL de água destilada.\n Assinale a alternativa que apresenta, corretamente, a concentração molar de ácido ascórbico na solução.\nDados: massas atômicas: H = 1 u; C = 12 u; O = 16 u ");
            a.setText("0,500 % mol ");
            b.setText("0,500 mol % ");
            c.setText("0,500 mol/L ");
            d.setText("0,568 mol/L ");
            e.setText("0,568 m ");
            alt = R.id.d;
        }
        if (question == 95) {
            questaoTextView.setText("A RDC ANVISA no20, de 5 de maio de 2011, dispõe sobre o controle de medicamentos à base de substâncias classificadas como antimicrobianos, de uso sob prescrição, isolado ou em associação. Com base nesta RDC, assinale a alternativa que apresenta um medicamento que NÃO está sujeito a tal regulamentação.");
            a.setText("Metronidazol.");
            b.setText("Ácido clavulânico.");
            c.setText("Nistatina.");
            d.setText("Eritromicina.");
            e.setText("Sulfadiazina.");
            alt = R.id.c;
        }
        if (question == 96) {
            questaoTextView.setText("Durante a estruturação da Relação Municipal de Medicamentos (REMUME) de seu Município, um farmacêutico teve que selecionar diuréticos que iriam compor tal lista. Assinale a alternativa que apresenta um diurético tiazídico, um de alça e um poupador de potássio, respectivamente.");
            a.setText("Amilorida / furosemida / hidroclorotiazida.");
            b.setText("Hidroclorotiazida / furosemida / espironolactona.");
            c.setText("Furosemida / espironolactona / acetazolamida.");
            d.setText("Furosemida / hidroclorotiazida / amilorida.");
            e.setText("Hidroclorotiazida / espironolactona / acetazolamida.");
            alt = R.id.b;
        }
        if (question == 97) {
            questaoTextView.setText("No ciclo da Assistência Farmacêutica, o armazenamento visa garantir a manutenção da segurança, eficácia e qualidade do medicamento. Com base nas recomendações de armazenamento de produtos farmacêuticos e medicamentos, assinale a alternativa correta.");
            a.setText("Quantidade excessiva de pó nas embalagens de comprimidos não indica alteração na estabilidade do medicamento.");
            b.setText("Fatores intrínsecos podem afetar a estabilidade de um produto farmacêutico como a luminosidade no interior do almoxarifado");
            c.setText("A maioria dos medicamentos da atenção básica deve ser armazenado em temperatura ambiente, sendo aceitável uma variação entre 15 ºC e 30ºC.");
            d.setText("A umidade não representa um fator que pode alterar a estabilidade química de um medicamento, apenas a biológica.");
            e.setText("O grau de umidade para armazenamento de medicamento deve ficar entre 0 e 30%");
            alt = R.id.c;
        }
        if (question == 98) {
            questaoTextView.setText("De acordo com o Código de Ética da Profissão Farmacêutica, o profissional que:\n 1º - interage com o profissional prescritor, quando necessário, para garantir a segurança e eficácia da terapêutica;\n 2º - respeita o direito de decisão do usuário sobre o seu tratamento, sua própria saúde e bem-estar, exceto em situações específicas e;\n 3º- exige dos profissionais de saúde o cumprimento da legislação sanitária vigente, está observando, respectivamente,");
            a.setText("um direito / um dever / um direito.");
            b.setText("um dever / um direito / um dever.");
            c.setText("um dever / um dever / um dever.");
            d.setText("um direito / um dever / um dever.");
            e.setText("um direito / um direito / um dever.");
            alt = R.id.a;
        }
        if (question == 99) {
            questaoTextView.setText("Alguns medicamentos são preferencialmente administrados com alimento, seja para aumentar a absorção ou para diminuir o efeito irritante sobre o estômago. De modo oposto, há medicamentos que têm a disponibilidade e a eficácia diminuídas se administrados com alimentos. Ante o exposto, assinale a alternativa correta.");
            a.setText("O albendazol deve ser ingerido longe das refeições.");
            b.setText("O sulfato ferroso é menos absorvido se administrado com suco de laranja.");
            c.setText("O AAS deve ser administrado após a refeição para aumentar sua eficácia.");
            d.setText("A amoxicilina não possui restrições quanto ao seu uso em jejum ou com alimentos.");
            e.setText("Nitrofurantoína, em hipótese alguma, deve ser ingerida com leite.");
            alt = R.id.d;
        }
        if (question == 100) {
            questaoTextView.setText("A respeito das vias de administração de fármacos, assinale a alternativa correta.");
            a.setText("Somente fármacos que agridem a mucosa estomacal é que são administrados em apresentações com revestimento.");
            b.setText("A via sublingual é adequadamente utilizada para inúmeros fármacos, devido à grande superfície de absorção.");
            c.setText("A via retal é, muitas vezes, útil quando o paciente encontra-se inconsciente e a ingestão oral não é possível.");
            d.setText("Fármacos aquosos são absorvidos de modo mais lento que os oleosos pela via intramuscular.");
            e.setText("A administração subcutânea de vasoconstritores acelera a absorção de anestésicos locais, potencializando a anestesia.");
            alt = R.id.c;
        }
        if (question == 101) {
            questaoTextView.setText("A farmacovigilância é o trabalho de acompanhamento do desempenho dos medicamentos que já estão no mercado. Sobre a farmacovigilância, assinale a alternativa correta.");
            a.setText("Para se notificar uma reação adversa, o farmacêutica deve ter a certeza de que o medicamento é a causa da reação.");
            b.setText("Somente as reações adversas graves é que devem ser notificadas.");
            c.setText("A subnotificação não influi no método de notificação espontânea adotado pela farmacovigilância.");
            d.setText("Além das reações adversas, desvios de qualidade de produtos farmacêuticos também devem ser notificados à ANVISA.");
            e.setText("Plantas medicinais bem como a medicina tradicional não são campos de observação e estudo da farmacovigilância.");
            alt = R.id.d;
        }
        if (question == 102) {
            questaoTextView.setText("A Politica Nacional de Promoção da Saúde (PNPS) afirma que, para o desenvolvimento da promoção da saúde é imprescindível intervir sobre problemas como: violência e desemprego. Em relação ao tema Promoção de saúde, assinale a alternativa correta.");
            a.setText("O aleitamento materno é uma ação de promoção da alimentação saudável.");
            b.setText("Caminhada não faz parte das ações de promoção, por tratar-se de uma atividade física leve sem impactos na saúde do praticante.");
            c.setText("O controle do tabagismo e do uso abusivo de álcool não são ações de promoção da saúde, uma vez que trata-se da recuperação da saúde de um indivíduo.");
            d.setText("A promoção da saúde foi substituída pela estratégia saúde da família, que atua junto às famílias na recuperação da saúde.");
            e.setText("O estímulo à alimentação saudável, embora contribua para a qualidade de vida e saúde de um indivíduo, não é uma ação de promoção da saúde.");
            alt = R.id.a;
        }
        if (question == 103) {
            questaoTextView.setText("Ao dispensar uma receita contendo um medicamento antimicrobiano, o farmacêutico(a) deve se atentar a uma série de aspectos quanto a legislação sanitária, como a RDC ANVISA 20/2011, especificamente. Ante o exposto, assinale a alternativa correta.");
            a.setText("A receita deve conter a identificação do paciente: nome completo, peso e altura.");
            b.setText("O farmacêutico deverá reter a 1ª via da receita.");
            c.setText("A receita contendo antimicrobiano terá validade de 7 dias em todo território nacional.");
            d.setText("Não é permitida a prescrição de mais de um antimicrobiano em uma mesma receita.");
            e.setText("A receita poderá conter a prescrição de outras categorias de medicamentos desde que não sejam sujeitas a controle especial.");
            alt = R.id.e;
        }
        if (question == 104) {
            questaoTextView.setText("Sobre a Portaria 344, de 12 de maio de 1998, que aprova o regulamento técnico sobre substâncias e medicamentos sujeitos a controle especial, assinale a alternativa correta.");
            a.setText("Substância proscrita é aquela em que o médico, ou outro profissional habilitado, a indicou a um paciente mediante receita específica.");
            b.setText("Notificação de Receita é o documento que, acompanhado de receita, autoriza a dispensação de medicamentos a base de substância da lista“C1”.");
            c.setText("A receita de Controle Especial contendo medicamentos da lista “C1” terá validade de 60 dias, contados a partir da data de sua emissão.");
            d.setText("Medicamentos antiparkinsonianos e anticonvulsivantes poderão ser prescritos com quantidades máximas limitadas a 60 dias de tratamento.");
            e.setText("A prescrição por cirurgiões-dentistas só poderá ser feita quando para uso odontológico.");
            alt = R.id.e;
        }
        if (question == 105) {
            questaoTextView.setText("Paciente asmático recebeu o diagnóstico de hipertensão arterial sistêmica e deverá iniciar o tratamento medicamentoso. Entretanto algumas drogas lhe são contraindicadas, pois podem desencadear broncoespasmo, com piora de asma e DPOC. Assinale a alternativa que apresenta uma droga que NÃO deve ser utilizada por este paciente devido a estes riscos.");
            a.setText("Losartana.");
            b.setText("Propranolol.");
            c.setText("Hidroclorotiazida.");
            d.setText("Anlodipino.");
            e.setText("Verapamil.");
            alt = R.id.b;
        }
        if (question == 106) {
            questaoTextView.setText("A programação da aquisição de medicamento representa um ponto-chave no ciclo da assistência farmacêutica. Sobre a programação, assinale a alternativa correta.");
            a.setText("A programação por perfil epidemiológico requer, obrigatoriamente, dados de consumo.");
            b.setText("O método de programação por consumo histórico consiste na análise do comportamento do consumo dos medicamentos com base em uma série histórica.");
            c.setText("O método de consumo histórico não é tão empregado, por necessitar de dados de morbimortalidade.");
            d.setText("O método de programação por perfil epidemiológico fundamenta-se principalmente no consumo médio mensal.");
            e.setText("Para calcular o consumo médio mensal, deve-se considerar inclusive meses em que ocorreu desabastecimento dos produtos.");
            alt = R.id.b;
        }
        if (question == 107) {
            questaoTextView.setText("Em relação à farmacocinética, assinale a alternativa correta.");
            a.setText("O fígado é o órgão mais importante para a excreção de fármacos e seus metabólitos.");
            b.setText("A extensão da ligação de fármacos a proteínas plasmáticas não é influenciada por patologias.");
            c.setText("O volume de distribuição é um parâmetro que se refere ao volume de sangue no organismo de um indivíduo.");
            d.setText("Meia-vida é o tempo necessário para que o fármaco aumente em 50% sua concentração no plasma.");
            e.setText("As reações de conjugação de fase II levam à formação de conjugados mais polares para serem excretados.");
            alt = R.id.e;
        }
        if (question == 108) {
            questaoTextView.setText("A Portaria no 3916, de 30 de outubro de 1998, aprova a Política Nacional de Medicamentos. A respeito desta política, assinale a alternativa correta.");
            a.setText("A RENAME serve de base para que estados e municípios organizem suas próprias listas.");
            b.setText("O modelo de assistência farmacêutica será reorientado de modo a se restringir à aquisição de medicamentos.");
            c.setText("A reorientação da assistência farmacêutica está fundamentadas na centralização da gestão.");
            d.setText("Como diretriz, esta política também traz a promoção do uso racional do medicamento e da automedicação.");
            e.setText("A RENAME não deverá servir de instrumento para a promoção do uso racional de medicamentos.");
            alt = R.id.a;
        }
        if (question == 109) {
            questaoTextView.setText("A farmacodinâmica é o campo da farmacologia que estuda os efeitos fisiológicos dos fármacos nos organismos vivos, seus mecanismos de ação e a relação entre concentração do fármaco e efeito. A respeito do assunto, assinale a alternativa INCORRETA.");
            a.setText("Os diuréticos de alça, como a furosemida, são potentes depletores de potássio.");
            b.setText("A clonidina é uma agonista alfa-2 de ação central indicada para hipertensão.");
            c.setText("Losartana atua como antagonista de receptor de angiotensina II.");
            d.setText("Fenoterol e Ipratrópio são duas drogas agonistas de receptores adrenérgicos.");
            e.setText("O ibuprofeno, ao bloquear a via das COXs, aumenta a produção de leucotrienos, devendo ser utilizado com precaução em pacientes asmáticos");
            alt = R.id.d;
        }
        if (question == 110) {
            questaoTextView.setText("A respeito do diagnóstico laboratorial do diabetes, assinale a alternativa correta.");
            a.setText("O exame de hemoglobina glicosilada não permite avaliar a concentração média de glicose plasmática em um paciente diabético.");
            b.setText("Pacientes admitidos em hospitais com cetoacidose diabética normalmente apresentam concentrações de potássio alteradas no plasma e na urina.");
            c.setText("A excreção de albumina urinária auxilia na avaliação do metabolismo das proteínas, porém não apresenta relação com nefropatia diabética.");
            d.setText("O exame de glicemia em jejum pode ser realizado após um período de jejum de 4 horas.");
            e.setText("A alta concentração de cetonas na urina indica uma baixa taxa de lipólise.");
            alt = R.id.b;
        }
        if (question == 111) {
            questaoTextView.setText("A eletroforese de hemoglobina é utilizada como teste de rastreamento para detecção e diferenciação das hemoglobinas anormais e suas variantes. Que material biológico e qual cor da tampa do tubo de coleta devem ser utilizados, respectivamente, para a realização desse exame?");
            a.setText("Plasma – tampa – vermelha.");
            b.setText("Sangue total – tampa – vermelha.");
            c.setText("Soro – tampa – vermelha.");
            d.setText("Sangue total – tampa lavanda – azul ou verde.");
            e.setText("Plasma tampa – roxa.");
            alt = R.id.d;
        }
        if (question == 112) {
            questaoTextView.setText("Em relação às possíveis alterações da série vermelha encontradas no hemograma, assinale a alternativa INCORRETA.");
            a.setText("Dacriócitos são eritrócitos em forma de gota ou lágrima.");
            b.setText("Acantócitos são eritrócitos contraídos, esferoides, com espículas de dimensão e distribuição irregulares.");
            c.setText("Drepanócitos têm a forma de foicinha ou banana, ao menos com uma das extremidades com ponta afilada.");
            d.setText("Os equinócitos podem ser encontrados in vivo no sangue de pacientes que fazem tratamento com heparina.");
            e.setText("Os esferócitos são eritrócitos elípticos e ovais.");
            alt = R.id.e;
        }
        if (question == 113) {
            questaoTextView.setText("Os anticorpos são importantes ferramentas do sistema imunológico na defesa do organismo humano frente à exposição aos patógenos. São células produtoras de anticorpos");
            a.setText("neutrófilos e basófilos.");
            b.setText("linfócitos B e linfócitos T.");
            c.setText("linfócitos B e plasmócitos.");
            d.setText("linfócitos T e segmentados.");
            e.setText("monócitos e eritrócitos.");
            alt = R.id.c;
        }
        if (question == 114) {
            questaoTextView.setText("As doenças autoimunes são um tipo de desordem imunológica e sua principal característica reside no fato da diminuição da tolerância aos componentes do próprio organismo. São doenças autoimunes as seguintes, EXCETO");
            a.setText("lupus eritematoso sistêmico.");
            b.setText("tireoidite de Hashimoto.");
            c.setText("doença de Graves.");
            d.setText("doença de Addison.");
            e.setText("anemia megaloblástica.");
            alt = R.id.e;
        }
        if (question == 115) {
            questaoTextView.setText("O fígado tem um papel central no metabolismo devido à sua localização anatômica e muitas de suas funções bioquímicas. Em relação às funções bioquímicas do fígado, assinale a alternativa correta.");
            a.setText("As doenças hepáticas moderadas normalmente não apresentam alterações bioquímicas no sangue.");
            b.setText("Dependendo das condições metabólicas, o fígado pode estocar glicose ou enviar glicose sob a forma de glicogênio livre para a corrente sanguínea.");
            c.setText("O fígado sintetiza as principais proteínas do organismo, porém não sintetiza a albumina.");
            d.setText("Os fatores de coagulação não se alteram nas doenças hepáticas.");
            e.setText("Uma das funções do fígado é produzir a glicose- 6-fosfatase, que leva ao acúmulo de glicose e produção de glicogênio nos hepatócitos.");
            alt = R.id.b;
        }
        if (question == 116) {
            questaoTextView.setText("Quais são os agentes mais comumente isolados em casos de meningite bacteriana?");
            a.setText("Cryptococcus neoformans, Streptococcus pneumoniae e Moraxella catarralis.");
            b.setText("Haemophilus influenzae, Proteus mirabilis e Cryptococcus neoformans.");
            c.setText("Herpes simples, Moraxella catarralis e Streptococcus pneumoniae.");
            d.setText("Haemophilus influenzae, Streptococcus pneumoniae e Neisseria meningitidis.");
            e.setText("Neisseria meningitidis, Histoplasma capsulatum e Proteus mirabilis.");
            alt = R.id.d;
        }
        if (question == 117) {
            questaoTextView.setText("Qual é o meio de cultura mais empregado para o crescimento de micobactérias?");
            a.setText("Lowestein-Jensen.");
            b.setText("Meio de Rugai.");
            c.setText("Ágar sangue.");
            d.setText("Ágar chocolate.");
            e.setText("Thayer-Martin.");
            alt = R.id.a;
        }
        if (question == 118) {
            questaoTextView.setText("São infecções virais sexualmente transmissíveis as seguintes condições, EXCETO");
            a.setText("HIV.");
            b.setText("herpes simples.");
            c.setText("sífilis.");
            d.setText("hepatite C.");
            e.setText("papiloma vírus humano.");
            alt = R.id.c;
        }
        if (question == 119) {
            questaoTextView.setText("O vírus da hepatite B é responsável por 350 milhões de infectados em todo o mundo. A respeito das infecções virais causadas por esse vírus, assinale a alternativa INCORRETA.");
            a.setText("O vírus da hepatite B pode ser detectado no sangue e derivados, saliva, sêmem e secreção vaginal.");
            b.setText("A hepatite B pode existir na forma assintomática, sendo possível a detecção em triagem de banco de sangue ou em exames de rotina.");
            c.setText("Na hepatite B aguda, ocorre aumento dos níveis séricos de TGO e TGP e aumento dos níveis de bilirrubina.");
            d.setText("Na fase aguda da hepatite B, encontram-se elevados os níveis séricos de AgHBs e AgHBe.");
            e.setText("O AgHBs permanece após 6 meses da infecção no sangue de pessoas que obtiveram cura para hepatite B.");
            alt = R.id.e;
        }
        if (question == 120) {
            questaoTextView.setText("Sobre o exame micológico direto, assinale a alternativa correta.");
            a.setText("O exame micológico direto é útil no diagnóstico de infecção fúngica, entretanto os resultados falso negativo e falso positivo podem ocorrer.");
            b.setText("A observação microscópica de hifas e leveduras normalmente requer um preparo da amostra que demora mais de 48 horas.");
            c.setText("A detecção microscópica dos fungos não possibilita orientar o laboratório na seleção do meio mais adequado para a cultura.");
            d.setText("O corante de Giemsa não deve ser utilizado no exame micológico direto, uma vez que esse corante destrói as estruturas das hifas.");
            e.setText("Ao utilizar a coloração de Gram, os fungos se coloram tipicamente como Gram-negativos, podendo raramente ficar descorados ou corados como Gram-positivos.");
            alt = R.id.a;
        }
        if (question == 121) {
            questaoTextView.setText("O exame parcial de urina é empregado no diagnóstico das mais diversas patologias. Sobre esse exame, assinale a alternativa INCORRETA.");
            a.setText("A amostra de urina não pode ser congelada.");
            b.setText("A hematúria pode ocorrer por diversos motivos, que vão desde um falso positivo atribuído à menstruação até infecções, cálculos renais e doenças renais graves.");
            c.setText("Os cilindros leucocitários indicam que pode haver infecção ou interpretação renal.");
            d.setText("A presença de cristais na urina é independente da dieta do paciente.");
            e.setText("A presença de bactérias na urina, junto com leucócitos e testes positivos de nitrito e leucócito esterase, indica processos infecciosos.");
            alt = R.id.d;
        }
        if (question == 122) {
            questaoTextView.setText("As culturas são feitas pela semeadura dos materiais clínicos em meios sólidos, distribuídos em placas de Petri, ou em meios líquidos, distribuídos em tubos de ensaio ou em outros tipos de frascos. A respeito da realização das culturas, assinale a alternativa correta.");
            a.setText("Os meios líquidos são utilizados como meio de enriquecimento ou quando é grande o volume do material clínico a ser semeado.");
            b.setText("Uma vez semeados, todos os meios devem ser incubados em temperatura acima de 45 ºC.");
            c.setText("A atmosfera isenta de O2 é a atmosfera necessária para o crescimento de microrganismos aeróbios.");
            d.setText("Quando se deseja obter um ambiente rico em O2 para o crescimento de bactérias, devese acender uma vela no fundo de uma jarra, fechando-se hermeticamente essa jarra.");
            e.setText("Os meios de cultura não devem ser preparados no próprio laboratório.");
            alt = R.id.a;
        }
        if (question == 123) {
            questaoTextView.setText("Qual das seguintes condições NÃO apresenta relação com causas genéticas?");
            a.setText("Anemia Falciforme.");
            b.setText("Talassemia.");
            c.setText("Esferocitose.");
            d.setText("Anemia Perniciosa.");
            e.setText("Deficiência de glicose-6-fosfato desidrogenase.");
            alt = R.id.d;
        }
        if (question == 124) {
            questaoTextView.setText("Qual das alternativas a seguir apresenta uma espécie de bactéria anaeróbia?");
            a.setText("Staphylococcus aureus.");
            b.setText("Streptococcus pyogenes.");
            c.setText("Clostridium botulinum.");
            d.setText("Neisseria meningitidis.");
            e.setText("Bordetella pertussis.");
            alt = R.id.c;
        }
        if (question == 125) {
            questaoTextView.setText("São preparações líquidas obtidas, normalmente, de substâncias de origem vegetal ou animal:");
            a.setText("suspensões.");
            b.setText("tinturas.");
            c.setText("xaropes.");
            d.setText("elixir.");
            e.setText("emulsão.");
            alt = R.id.b;
        }
        if (question == 126) {
            questaoTextView.setText("Nas comissões de controle de infecção hospitalar a atuação do farmacêutico é, no mínimo:");
            a.setText("permanente.");
            b.setText("consultor.");
            c.setText("executor.");
            d.setText("colaborador.");
            e.setText("observador.");
            alt = R.id.b;
        }
        if (question == 127) {
            questaoTextView.setText("Quando for prescrito o uso de água isenta de dióxido de carbono, pode-se utilizar:");
            a.setText("água destilada.");
            b.setText("água deionizada.");
            c.setText("água tridestilada.");
            d.setText("recém-deionizada e fervida.");
            e.setText("recém-destilada e fervida.");
            alt = R.id.e;
        }
        if (question == 128) {
            questaoTextView.setText("Identifique o principal grupo de anti-inflamatórios NÃO esteroidais.");
            a.setText("Inibidor da via da ciclooxigenase.");
            b.setText("Derivado fenólico.");
            c.setText("Antagonista das vias de ativação da N-Metil-DAspartato.");
            d.setText("Opiáceo.");
            e.setText("Corticoide.");
            alt = R.id.a;
        }
        if (question == 129) {
            questaoTextView.setText("O amido é muito utilizado como desintegrante de comprimidos.A melhor maneira de analisar sua pureza é através da:");
            a.setText("Espectrofotometria.");
            b.setText("Cromatografia.");
            c.setText("Eletroforese.");
            d.setText("Titulação.");
            e.setText("Espectrometria de absorção.");
            alt = R.id.d;
        }
        if (question == 130) {
            questaoTextView.setText("Qual a capacidade de absorção de água que os papéis de filtro devem ter, para o preparo dos discos de inibição, com a finalidade de avaliar antimicrobianos?");
            a.setText("Metade de seu peso.");
            b.setText("Uma vez o seu peso.");
            c.setText("Uma a duas vezes seu peso.");
            d.setText("De meio a uma vez e meia o seu peso.");
            e.setText("De duas a quatro e meia vezes o seu peso.");
            alt = R.id.e;
        }
        if (question == 131) {
            questaoTextView.setText("A calibração de um refratômetro para a determinação do índice de refração de um líquido deve ser realizada com uma substância que apresenta índice de refração 1,333 a 20 °C ou 1,325 a 25 °C. Esta substância é:");
            a.setText("água.");
            b.setText("benzeno.");
            c.setText("acetona.");
            d.setText("etanol.");
            e.setText("propanol.");
            alt = R.id.a;
        }
        if (question == 132) {
            questaoTextView.setText("A avaliação da granulosidade de um pó é determinada colocando-se uma amostra sobre o tamis de malha apropriada, provido de tampa e recipiente apropriado para coleta do pó. Um pó cujas partículas passam na sua totalidade, em uma malha nominal de 355 μm e no máximo 40% em malha nominal de180μméumpó:");
            a.setText("grosso.");
            b.setText("moderadamente grosso.");
            c.setText("semifino.");
            d.setText("fino.");
            e.setText("finíssimo.");
            alt = R.id.c;
        }
        if (question == 133) {
            questaoTextView.setText("Ao procedimento de validar um processo durante sua produção de rotina, como no caso de diferentes concentrações do mesmo produto, chamamos de validação:");
            a.setText("concorrente.");
            b.setText("inicial.");
            c.setText("retrospectiva.");
            d.setText("confirmatória.");
            e.setText("acessória.");
            alt = R.id.a;

        }
        if (question == 134) {
            questaoTextView.setText("Um ato documentado que atesta que qualquer procedimento, processo, equipamento, material, operação ou sistema realmente conduza aos resultados esperados dentro do contexto da atividade farmacêutica é chamado de:");
            a.setText("documentação.");
            b.setText("análise.");
            c.setText("validação.");
            d.setText("garantia de qualidade.");
            e.setText("boas práticas de farmácia.");
            alt = R.id.c;

        }
        if (question == 135) {
            questaoTextView.setText("Para o processo de validação da limpeza na produção de um medicamento em pó, a principal propriedade da melhor substância candidata a contaminante para ensaio é aquela que apresenta:");
            a.setText("maior toxicidade.");
            b.setText("menor dose terapêutica.");
            c.setText("remoção mais difícil pela experiência dos operadores.");
            d.setText("menor solubilidade nos solventes utilizados para limpeza.");
            e.setText("maior dose terapêutica.");
            alt = R.id.d;

        }
        if (question == 136) {
            questaoTextView.setText("Um medicamento aplicado na pele com resposta sistêmica é um medicamento:");
            a.setText("tópico.");
            b.setText("local.");
            c.setText("dérmico.");
            d.setText("transdérmico.");
            e.setText("epidérmico.");
            alt = R.id.d;

        }
        if (question == 137) {
            questaoTextView.setText("Quando a nutrição parenteral requer adição de um medicamento, esse procedimento:");
            a.setText("deve ser realizado com uma seringa na própria enfermaria.");
            b.setText("deve ser realizado na farmácia.");
            c.setText("a farmácia deve dispor de um local exclusivo para esse procedimento.");
            d.setText("deve ocorrer em posto da enfermaria.");
            e.setText("o posto da enfermaria deve ter local isolado para esse procedimento.");
            alt = R.id.c;
        }
        if (question == 138) {
            questaoTextView.setText("A aspirina e a fenilbutazona quando administradas de modo concomitante com o warfarin, elevam a concentração sérica do anticoagulante e o potencial hemorrágico porque alteram a:");
            a.setText("absorção.");
            b.setText("distribuição.");
            c.setText("metabolização.");
            d.setText("excreção.");
            e.setText("interação farmacodinâmica.");
            alt = R.id.b;
        }
        if (question == 139) {
            questaoTextView.setText("Um adjuvante para preparações de uma forma farmacêutica líquida que fornece sabor, é o:");
            a.setText("solubilizante.");
            b.setText("conservante.");
            c.setText("aromatizante.");
            d.setText("corante.");
            e.setText("edulcorante.");
            alt = R.id.e;
        }
        if (question == 140) {
            questaoTextView.setText("No sistema de gerenciamento da farmácia hospitalar, o envio de medicamento à enfermaria em embalagens originais com a identificação do paciente, leito, e demais dados necessários para o consumo em 24 horas é um procedimento de distribuição chamado de:");
            a.setText("coletivo.");
            b.setText("unitário.");
            c.setText("individualizado.");
            d.setText("identificado.");
            e.setText("diário.");
            alt = R.id.c;
        }
        if (question == 141) {
            questaoTextView.setText("Qual a temperatura ambiente recomendada para a conservação de medicamentos em farmácia hospitalar?");
            a.setText("15 °C a 20 °C.");
            b.setText("17 °C a 20 °C.");
            c.setText("20 °C a 22 °C.");
            d.setText("22 °C a 25 °C.");
            e.setText("15 °C a 30 °C.");
            alt = R.id.c;
        }
        if (question == 142) {
            questaoTextView.setText("A farmacoepidemiologia pode ser definida como o estudo da utilização e dos efeitos de fármacos em um grande número de pessoas. Para realizar este estudo, a farmacoepidemiologia utiliza conhecimentos da farmacologia e da epidemiologia, constituindo-se assim como uma ciência de ponte entre essas duas. Desta forma, julgue as afirmações abaixo:\n I - Por meio dos estudos de farmacoepidemiológicos, consegue-se uma estimativa da probabilidade de efeitos benéficos ou adversos em populações, além de outros parâmetros relativos ao uso de fármacos. No entanto, a farmacoepidemiologia não pode ser utilizada para garantir a vigilância de fármacos na fase de comercialização já que isso é uma atribuição da farmacovigilância.\n II - A farmacovigilância é o processo de identificar e responder sobre assuntos de segurança a respeito de fármacos comercializados e foi com base nela que a farmacoepidemiologia foi fundada.\n III - A farmacoepidemiologia tem o potencial para examinar os impactos dos fatores prescritos, pacientes, doenças e de fatores econômicos no uso de fármacos, porém não contribui para as questões no âmbito da legislação farmacêutica.\n IV - Um maior acesso aos conceitos e métodos farmacoepidemiológicos tem levado a uma melhora na utilização adequada dos medicamentos por médicos, dentistas e farmacêuticos.\n Estão corretas apenas:");
            a.setText("II e IV");
            b.setText("I e II");
            c.setText("I e III");
            d.setText("III e IV");
            e.setText("II e III");
            alt = R.id.a;
        }
        if (question == 143) {
            questaoTextView.setText("O profissional farmacêutico deve por oficio fazer uma diferenciação entre o sistema de distribuição por dose unitária e dose unitária de medicamentos. O conceito de distribuição por dose unitária é a distribuição ordenada dos medicamentos com formas e dosagens prontas para serem administradas a um determinado paciente de acordo com a prescrição médica, num certo período de tempo. A dose unitária industrial corresponde à dose padrão comercializada pelos laboratórios, fornecida em embalagem unitária, em que constam a correta identificação do fármaco, prazo de validade, lote, nome comercial e outras informações. Sobre sistemas de distribuição e dispensação de medicamentos, é CORRETO AFIRMAR:");
            a.setText("Uma das causas do aumento dos erros de distribuição e administração de medicamentos no sistema de distribuição por dose unitária é que na unidade assistencial estarão estocados somente os medicamentos que atendem os casos de emergências, antissépticos e as doses necessárias para suprir as próximas 24 horas de tratamento do paciente.");
            b.setText("A embalagem para sólidos de uso oral deve ser suficiente para liberar o conteúdo total etiquetado. É aconselhável que seja necessário um acréscimo de volume conhecido, dependendo da forma de envase, do material e da formulação do medicamento.");
            c.setText("Uma das vantagens do sistema de distribuição por dose unitária é a identificação do medicamento até o momento de sua administração, com a necessidade de transferências e cálculos, o que reduz a incidência de erros de administração do medicamento.");
            d.setText("Uma das desvantagens do sistema de distribuição por dose unitária é a adaptabilidade a sistemas automatizados e computadorizados.");
            e.setText("O fracionamento ou reembalagem de medicamentos para o sistema de distribuição individualizado e/ou por dose unitária deve se efetuar em condições semelhantes às utilizadas pelo fabricante, de forma a impedir tanto uma possível alteração de estabilidade como a contaminação cruzada ou microbiana.");
            alt = R.id.e;
        }
        if (question == 144) {
            questaoTextView.setText("Entende-se por assistência farmacêutica o conjunto de ações e de serviços que visem a assegurar a assistência terapêutica integral e a promoção, a proteção e a recuperação da saúde nos estabelecimentos públicos e privados que desempenham atividades farmacêuticas, tendo o medicamento como insumo essencial e visando ao seu acesso e ao seu uso racional. Com base nos dizeres da Lei no 13.021 de 8 de Agosto de 2014, que dispõe sobre o exercício e a fiscalização das atividades farmacêuticas, todas as alternativas estão corretas, EXCETO:");
            a.setText("É vedado ao fiscal farmacêutico exercer outras atividades profissionais de farmacêutico, ser responsável técnico ou proprietário ou participar da sociedade em estabelecimentos farmacêuticos.");
            b.setText("O farmacêutico e o proprietário dos estabelecimentos farmacêuticos agirão sempre solidariamente, realizando todos os esforços para promover o uso racional de medicamentos, e o proprietário da farmácia não poderá desautorizar ou desconsiderar as orientações técnicas emitidas pelo farmacêutico.");
            c.setText("Para o funcionamento das farmácias de qualquer natureza, exigem-se a autorização e o licenciamento da autoridade competente, além de ter a presença do farmacêutico durante 40 horas semanais.");
            d.setText("Cabe ao farmacêutico, na dispensação de medicamentos, visando a garantir a eficácia e a segurança da terapêutica prescrita, observar os aspectos técnicos e legais do receituário.");
            e.setText("Obriga-se o farmacêutico, no exercício de suas atividades, a notificar os profissionais de saúde e os órgãos sanitários competentes, bem como o laboratório industrial, dos efeitos colaterais, das reações adversas, das intoxicações, voluntárias ou não, e da farmacodependência observados e registrados na prática da farmacovigilância.");
            alt = R.id.c;
        }
        if (question == 145) {
            questaoTextView.setText("Além da substância ativa (fármaco), os comprimidos contêm um grande número de adjuvantes, cujo papel é permitir que a operação de compressão ocorra satisfatoriamente e assegure que os comprimidos sejam obtidos com a qualidade especificada. Assim sendo, o tipo de excipiente adicionado à mistura fármaco-material de enchimento, que assegura que os grânulos e os comprimidos sejam formados com resistência mecânica desejada, é conhecido como:");
            a.setText("Aglutinante");
            b.setText("Lubrificante");
            c.setText("Desintegrante");
            d.setText("Absorvente");
            e.setText("Emoliente");
            alt = R.id.a;
        }
        if (question == 146) {
            questaoTextView.setText("Em muitas situações, no tratamento de uma enfermidade, única ou associada a outras patologias, para alcançar uma resposta terapêutica eficaz, faz-se necessário o emprego de dois ou mais fármacos, administrados concomitantemente ou em tempos distintos. Portanto, em muitas situações, a associação de fármacos pode ser necessária, especialmente quando se tem os seguintes objetivos, EXCETO:");
            a.setText("Quando o efeito resultante da interação de dois fármacos é maior do que os efeitos individuais (sinergismo) – exemplo Trimetoprima, um antibacteriano de amplo espectro, é comercializado em associação com o Sulfametoxazol.");
            b.setText("Quando os efeitos são aditivos (adição de efeitos ou somação), ou seja, o efeito resultante corresponde à soma dos efeitos individuais, apresentando os fármacos associados mecanismos diferentes na normalização de uma função fisiológica desajustada.");
            c.setText("Quando os componentes da associação atuam em um mesmo microrganismos. A anfotericina B é um antifúngico de amplo espectro utilizado em associação com a tetraciclina como agente antiacne.");
            d.setText("Em pacientes portadores de patologias crônicas (bronquite, hipertensão, gota, diabete melito, etc.) ou de tratamento prolongado, como úlceras pépticas, que necessitam de um novo fármaco pela ocorrência de patologia transitória (processo infeccioso, inflamatório, etc.).");
            e.setText("Quando os componentes da associação são necessários por atuarem em situações diferentes causadoras de uma mesma patologia. O + + esomeprazol age por inibição da H K /ATPase, gerando redução na secreção de HCl no estômago, basal ou estimulada pela gastrina. O omeprazol é utilizado no tratamento das ulcerações gástricas e duodenais associadas com a presença do bacilo Gram-negativo Helicobacter pylori.");
            alt = R.id.c;
        }
        if (question == 147) {
            questaoTextView.setText("Comprimidos são formas farmacêuticas sólidas, geralmente preparadas com o auxílio de adjuvantes farmacêuticos. Eles podem variar em tamanho, forma, peso, dureza, espessura, características de desintegração e de dissolução e outros aspectos, dependendo de sua finalidade de uso e seu método de fabricação. Sobre os tipos de Comprimidos, assinale a alternativa INCORRETA:");
            a.setText("Os comprimidos mastigáveis, que se desintegram rapidamente pela mastigação, apresentam uma base cremosa, constituída em geral de manitol com a adição de corantes e flavorizantes.");
            b.setText("Os comprimidos bucais são desenvolvidos de modo a sofrerem erosão rápida proporcionando efeito rápido do fármaco.");
            c.setText("Os comprimidos efervescentes são preparados por meio da compactação de sais efervescentes granulados que liberam gás quando em contato com a água. O “efeito das bolhas” auxilia na desagregação dos comprimidos e aumenta a dissolução do fármaco.");
            d.setText("Os comprimidos de liberação imediata são destinados a desintegrar e liberar o fármaco sem que haja o controle da velocidade, como nos comprimidos que contêm revestimentos especiais ou outras tecnologias.");
            e.setText("Os comprimidos peliculados contêm uma fina camada de um polímero capaz de formar uma película. O filme é geralmente colorido, sendo vantajoso em relação ao revestimento de açúcar por ser mais durável, menos volumoso e requerer menor tempo de aplicação. O material empregado no revestimento é selecionado para romper e expor o núcleo do comprimido no local adequado do trato gastrintestinal.");
            alt = R.id.b;
        }
        if (question == 148) {
            questaoTextView.setText("Preparações farmacêuticas são aplicadas topicamente nos olhos para tratar condições superficiais ou intraoculares, incluindo infecções bacterianas, fúngicas e virais dos olhos ou pálpebras, conjuntivites alérgicas ou infecciosas, glaucoma e síndrome do olho seco decorrente de produção inadequada de fluidos de irrigação dos olhos. Sobre soluções farmacêuticas oftálmicas estão corretas as afirmativas abaixo, EXCETO:");
            a.setText("Fármacos utilizados pela via oftálmica devem ser estáveis no pH fisiológico do olho (7,4) para garantir as suas eficácias.");
            b.setText("Os fármacos ligados às proteínas são incapazes de penetrar o epitélio corneal devido ao tamanho do complexo proteína-fármaco formado.");
            c.setText("Enzimas presentes na lágrima podem metabolizar o fármaco e inviabilizar o tratamento.");
            d.setText("A córnea apresenta camadas lipofílica e hidrofílica, portanto fármacos com características lipofílicas e hidrofílicas são mais permeáveis a esta barreira.");
            e.setText("O uso de soluções viscosas para uso oftálmico melhora a biodisponibilidade dos fármacos utilizados por esta via de administração, devido a um aumento do tempo de contato do fármaco com os tecidos.");
            alt = R.id.a;
        }
        if (question == 149) {
            questaoTextView.setText("As emulsões farmacêuticas geralmente consistem na mistura de uma fase aquosa com vários óleos e/ou ceras. Quando as gotículas de óleo estão dispersas na fase aquosa, tem-se uma emulsão óleo-em-água (O/A). Sistemas nos quais a água encontra-se dispersa em óleo constituem emulsões água-em-óleo (A/O). Sobre emulsões, assinale a alternativa CORRETA.");
            a.setText("O tensoativo tem como função formar um filme interfacial entre a fase aquosa e oleosa aumentando a tensão inferfacial entre eles.");
            b.setText("As emulsões são sistemas conhecidos por serem estáveis, pois apresentam Energia Livre de Gibbs (G) maior do que zero, o que caracteriza um sistema termodinamicamente estável.");
            c.setText("As emulsões A/O são apropriadas para uso pela via intravenosa (IV), pois sua fase externa é aquosa, impedindo a formação de embolia.");
            d.setText("O processo de coalescência que ocorre durante as etapas de instabilidade de uma emulsão é um processo reversível.");
            e.setText("Tensoativos com Equilíbrio Hidrófilo-Lipófilo (EHL) elevados (acima de 10) são os mais apropriados para produção de emulsões do tipo O/A.");
            alt = R.id.e;
        }
        if (question == 150) {
            questaoTextView.setText("A Resolução N 585, de 29 de agosto de 2013, regulamenta as atribuições clínicas do farmacêutico e dá outras providências. Sobre esta resolução, julgue as afirmativas abaixo quanto às atribuições clínicas do farmacêutico relativas aos cuidados à saúde, nos âmbitos individuais e coletivos.\n I - Realizar e registrar intervenções farmacêuticas junto ao paciente, família, cuidadores e sociedade. II - Fazer evolução do paciente e registrar no prontuário do paciente.\n III - Prescrever no âmbito da sua competência profissional.\n IV - Monitorar níveis terapêuticos de medicamentos, por meio de dados de farmacocinética clínica.\n Estão corretas as afirmativas:");
            a.setText("I e II, apenas.");
            b.setText("I, II, III e IV.");
            c.setText("I e III, apenas.");
            d.setText("I, II e IV apenas.");
            e.setText("II, III e IV, apenas.");
            alt = R.id.b;
        }
        if (question == 151) {
            questaoTextView.setText("Com relação à Portaria N 344, de 12 de maio de 1998, que aprova o regulamento técnico sobre substâncias e medicamentos sujeitos a controle especial, assinale a alternativa correta.");
            a.setText("As pessoas que chegam ao Brasil, em viagens internacionais, podem transportar medicamentos dessa portaria sem prescrição médica, desde que a quantidade transportada seja suficiente para apenas 30 dias de tratamento.");
            b.setText("As empresas importadoras dessas substâncias precisam ter um cadastro na Secretaria de Vigilância Sanitária do Ministério da Saúde, ficando isentas de fixação de cotas de importação.");
            c.setText("Quanto à comercialização, o estoque de substâncias e medicamentos de que trata esta portaria não poderá ser superior às quantidades previstas para atender as necessidades de 3 (três) anos de consumo.");
            d.setText("A Notificação de Receita 'A' será válida por 30 (trinta) dias a contar da data de sua emissão em todo o Território Nacional, sendo necessário que seja acompanhada da receita médica com justificativa do uso, quando para aquisição em outra Unidade Federativa.");
            e.setText("A prescrição poderá conter, em cada receita, no máximo 3 (três) substâncias constantes da lista 'C1' (outras substâncias sujeitas a controle especial) deste Regulamento Técnico e de suas atualizações, ou medicamentos que as contenham.");
            alt = R.id.d;
        }
        if (question == 152) {
            questaoTextView.setText("Estocar e administrar um almoxarifado de medicamentos não é como estocar alimentos – apesar da importância das duas atividades para a saúde humana. O alimento estragado, na maioria das vezes, é facilmente identificável. No caso dos medicamentos a realidade é outra: se eles tem o seu estado normal alterado, tornam-se inativos ou nocivos à saúde e, o que é pior, são de difícil reconhecimento. Sobre o processo de estocagem, julgue as afirmativas abaixo:\n I - A estocagem nunca deve ser efetuada em contacto direto com o solo e nem em lugar que receba luz solar direta, com exceção dos medicamentos não termossensíveis, que podem sofrer ação direta da luz solar.\n II - Os estoques devem ser inspecionados com frequência para verificar-se qualquer degradação visível, especialmente se os medicamentos ainda estiverem sob garantia de seus prazos de validade.\n III - Medicamentos com prazos de validade vencidos devem ser baixados do estoque e destruídos, com registro justificado por escrito pelo farmacêutico responsável, obedecendo ao disposto na legislação vigente.\n IV - A liberação de medicamentos para entrega deve obedecer à ordem cronológica de seus lotes de fabricação, ou seja, expedição dos lotes mais recentes antes dos mais antigos.\n Estão corretas apenas as afirmativas:");
            a.setText("II, III e IV.");
            b.setText("I e II.");
            c.setText("III e IV.");
            d.setText("I, II e IV.");
            e.setText("II e III.");
            alt = R.id.e;
        }
        if (question == 153) {
            questaoTextView.setText("Reação adversa a medicamento pode ser definida como “qualquer efeito prejudicial ou indesejado que se apresente após a administração de doses normais utilizadas no homem para a profilaxia, diagnóstico ou o tratamento de uma enfermidade”. Assinale a alternativa que NÃO é um tipo de reação adversa a medicamento:");
            a.setText("Efeito colateral.");
            b.setText("Idiossincrasia.");
            c.setText("Hipersensibilidade alérgica.");
            d.setText("Efeito cronofarmacológico.");
            e.setText("Superdosagem relativa.");
            alt = R.id.d;
        }
        if (question == 154) {
            questaoTextView.setText("O termo hepatite viral é utilizado para designar alterações hepáticas associadas a agentes infecciosos virais. Sobre a infecção pelo vírus da hepatite B e os marcadores sorológicos dessa patologia, assinale a alternativa correta. ");
            a.setText("O HBeAg é o primeiro marcador que aparece no curso da infecção pelo HBV.");
            b.setText("A presença de anti-HBs por mais de seis meses é indicativa de hepatite crônica.");
            c.setText("O HBeAg é um indicador de replicação viral. Sua positividade indica alta infectividade.");
            d.setText("O anti-HBc IgG é um marcador de infecção recente e permanece no soro até 6 meses após a infecção.");
            e.setText("O anti-HBc IgM é um marcador de longa duração, presente nas infecções agudas e crônicas.");
            alt = R.id.c;
        }
        if (question == 155) {
            questaoTextView.setText("Sobre as embalagens dos medicamentos descritos na portaria 344/98, assinale a alternativa INCORRETA. ");
            a.setText("Os medicamentos descritos na portaria 344/98 devem ser comercializados em embalagens invioláveis e de fácil identificação.");
            b.setText("Nas bulas e rótulos dos medicamentos que contêm misoprostrol, deve constar obrigatoriamente a expressão: “Atenção: risco para mulheres grávidas”-“Venda e uso restrito a hospital”.");
            c.setText("Os rótulos de embalagens de medicamentos a base de substâncias constantes das listas “A1”, “A2” e“A3” deverão ter uma faixa horizontal de cor preta, abrangendo todos os lados, com os dizeres: “Venda sob prescrição médica”-“Atenção: pode causar dependência física ou psíquica”.");
            d.setText("Nos casos dos medicamentos contendo a substância Anfepramona, deverá constar, em destaque, em rótulo e bula, a frase: “Atenção, este medicamento pode causar hipertensão pulmonar”.");
            e.setText("É permitido às drogarias o fracionamento dos medicamentos constantes da portaria 344/98. ");
            alt = R.id.e;
        }
        if (question == 156) {
            questaoTextView.setText("Qual das alternativas a seguir apresenta apenas infecções fúngicas que são classificadas como micoses oportunistas?");
            a.setText("Candidíase sistêmica, aspergilose e criptococose. ");
            b.setText("Pitiríase versicolor, candidíase das unhas e peniciliose. ");
            c.setText("Dermatofitose, histoplasmose e piedra branca. ");
            d.setText("Cromoblastomicose, piedra negra e pneumonia por Pneumocystis. ");
            e.setText("Paracoccidioidomicose, tinea negra e dermatofitose. ");
            alt = R.id.a;
        }
        if (question == 157) {
            questaoTextView.setText("Na farmácia hospitalar, a distribuição de medicamentos pelo sistema de dose unitária tem como objetivo garantir que os medicamentos prescritos cheguem ao paciente de forma segura e higiênica, garantindo a eficácia do esquema terapêutico prescrito. Sobre o sistema de distribuição de medicamentos por dose unitária, assinale a alternativa correta.");
            a.setText("Uma das vantagens da adoção desse sistema é a ausência de estoques periféricos de medicamentos nos diversos setores do hospital.");
            b.setText("Nesse sistema, o médico faz a prescrição em duas vias ou prescreve em apenas uma via e a enfermagem transcreve a prescrição.");
            c.setText("A farmácia separa os medicamentos por setor para um período de 24 horas. Após esse período, a enfermagem pode devolver à farmácia os medicamentos que não foram utilizados.");
            d.setText("Não é necessário que a farmácia faça triagem da prescrição médica.");
            e.setText("Após preparadas as tiras de medicamentos pela farmácia, não é necessário que a enfermagem faça a conferência dessas tiras.");
            alt = R.id.a;
        }
        if (question == 158) {
            questaoTextView.setText("Os resultados dos testes de suscetibilidade antimicrobiana in vitro são importantes para selecionar os agentes quimioterápicos ativos contra o organismo infectante. Sobre esses testes, assinale a alternativa correta. ");
            a.setText("Nos testes de difusão em ágar, diluições seriadas de um antibiótico são preparadas em meio de cultura nutriente e, em seguida, inoculadas com uma concentração padronizada da bactéria-teste.");
            b.setText("A concentração mais alta de antibiótico que é capaz de inibir o crescimento bacteriano é referida como a concentração inibitória máxima (CIM).");
            c.setText("Nos testes de diluição em caldo, uma concentração padronizada da bactéria é semeada sobre a superfície do meio de cultura e, em seguida, discos de papel filtro ou tiras impregnadas com antibióticos são colocados no caldo.");
            d.setText("A realização dos testes de suscetibilidade antimicrobiana contribui para a diminuição do aparecimento de cepas multirresistentes. ");
            e.setText("O método de difusão em ágar é um método quantitativo de testar a suscetibilidade antimicrobiana. Nesse método, os halos de inibição são medidos e categorizados como sensível, intermediário ou resistente.");
            alt = R.id.d;
        }
        if (question == 159) {
            questaoTextView.setText("O financiamento para aquisição dos medicamentos do Componente de Medicamentos de Dispensação Excepcional (CMDE) é de responsabilidade");
            a.setText("exclusivamente dos municípios. ");
            b.setText("exclusivamente dos estados. ");
            c.setText("exclusivamente do Ministério da Saúde.");
            d.setText("dos estados e do Ministério da Saúde.");
            e.setText("dos estados e dos municípios. ");
            alt = R.id.d;
        }
        if (question == 160) {
            questaoTextView.setText("Sobre a urinálise, assinale a alternativa correta.");
            a.setText("A urina vermelha é produzida na infecção por Pseudomonas ou na terapia com amitriptilina.");
            b.setText("Embora não sejam úteis na identificação de glicose e cetona na urina, as fitas reativas fornecem informação sobre proteínas, bilirrubina e hemácias no sedimento urinário.");
            c.setText("Elevadas concentrações de ácido ascórbico na urina não alteram os resultados de glicose urinária.");
            d.setText("Um resultado negativo para nitrito exclui totalmente a possibilidade de bacteinúria significativa.");
            e.setText("A presença de leucócitos na urina é um dos indicativos de infecção no trato urinário.");
            alt = R.id.e;
        }
        if (question == 161) {
            questaoTextView.setText("A aquisição de medicamentos é uma das principais atividades da Gestão da Assistência Farmacêutica e deve estar estreitamente vinculada às ofertas de serviços e à cobertura assistencial dos programas de saúde. Sobre a aquisição de medicamentos no SUS, assinale a alternativa correta. ");
            a.setText("É vedado ao município realizar a aquisição de medicamentos por meio da cooperação de outros municípios. ");
            b.setText("A programação de medicamentos no município tem como objetivo principal estabelecer uma relação municipal de medicamentos essenciais (REMUME). ");
            c.setText("A programação de medicamentos no município deve prever e favorecer a descontinuidade do abastecimento visando reduzir o custo com esses insumos. ");
            d.setText("As compras de medicamentos no município podem ser feitas por meio de licitação, dispensa de licitação e inexigibilidade de licitação, dependendo de cada situação. ");
            e.setText("A aquisição de medicamentos não deve ser realizada por licitação na modalidade registro de preço. ");
            alt = R.id.d;
        }
        if (question == 162) {
            questaoTextView.setText("As alterações morfológicas dos eritrócitos podem ser decorrentes de três situações distintas: envelhecimento da célula, artefato técnico e patologias intra ou extracelulares. Sobre essas alterações, assinale a alternativa INCORRETA. ");
            a.setText("A presença de dacriócitos é característica de esferocitose hereditária. ");
            b.setText("Hemácias em alvo podem ser encontradas nas talassemias, hepatopatias e ferropenias. ");
            c.setText("Os drepanócitos são característicos de doença falciforme. ");
            d.setText("Os macrovalócitos são comuns na anemia megaloblástica. ");
            e.setText("Corpúsculos de Howell-Jolly são característicos de asplenia funcional ou anatômica. ");
            alt = R.id.a;
        }
        if (question == 163) {
            questaoTextView.setText("A Portaria nº 2616, de 12 de maio de 1998, que trata do controle das infecções hospitalares, define que nos hospitais com leitos destinados a pacientes críticos os membros executores da Comissão de Controle de Infecção Hospitalar (CCIH) devem ter acrescidas 2 (duas) horas semanais de trabalho para cada 10 leitos. De acordo com a portaria, não são pacientes críticos para infecção hospitalar ");
            a.setText("pacientes de terapia intensiva. ");
            b.setText("puérperas de baixo risco. ");
            c.setText("pacientes de berçário de alto risco. ");
            d.setText("pacientes queimados. ");
            e.setText("pacientes submetidos a transplantes de órgãos. ");
            alt = R.id.b;
        }
        if (question == 164) {
            questaoTextView.setText("Assinale a alternativa correta sobre o metabolismo das enzimas. ");
            a.setText("A fosfatase alcalina está presente em elevadas concentrações no fígado e raramente é encontrada nos ossos. ");
            b.setText("A gama-glutamil-transferase está presente em concentrações elevadas no fígado, rins e pâncreas. ");
            c.setText("Quando ocorre lesão muscular esquelética ou cardíaca, geralmente há diminuição dos níveis plasmáticos de creatinoquinase. ");
            d.setText("A amilase é uma enzima produzida principalmente pelas glândulas mamárias. Sua atividade está aumentada principalmente em casos de pancreatite aguda. ");
            e.setText("Na intoxicação por inseticidas organofosforados, há elevação acentuada da atividade plasmática da colinesterase. ");
            alt = R.id.b;
        }
        if (question == 165) {
            questaoTextView.setText("As ações do fármaco no organismo dependem da farmacodinâmica e da farmacocinética dos fármacos. Sobre o assunto, assinale a alternativa correta. ");
            a.setText("Fármacos agonistas ligam-se ao receptor celular e o inativam. ");
            b.setText("Os agonistas farmacológicos, ao se ligarem a um receptor, competem e previnem a ligação por outras moléculas. ");
            c.setText("A difusão passiva de um fármaco é um processo de permeação em que ocorre gasto de energia. ");
            d.setText("Não existem fármacos que entram nas células por endocitose. ");
            e.setText("A exocitose é responsável pela secreção de várias substâncias pelas células. ");
            alt = R.id.e;
        }
        if (question == 166) {
            questaoTextView.setText("O fígado é de vital importância no metabolismo e na desintoxicação e eliminação de substâncias tóxicas. Para avaliar a função hepática, pode-se dosar diversos marcadores. Sobre os exames laboratoriais que avaliam a função hepática, assinale a alternativa INCORRETA. ");
            a.setText("A bilirrubinúria reflete um aumento na concentração plasmática de bilirrubina conjugada, o que caracteriza uma circunstância patológica. ");
            b.setText("A hiperbilirrubinemia deve ser exclusivamente associada à doença hepática. ");
            c.setText("A atividade da alanina aminotransferase apresenta-se normalmente elevada na hepatite crônica. ");
            d.setText("Em pacientes com doença hepática alcoólica, os níveis de gama-glutamil-transferase estão elevados. ");
            e.setText("A síndrome de Gilbert é caracterizada pela diminuição da conjugação de bilirrubina e da absorção dela em alguns casos. ");
            alt = R.id.b;
        }
        if (question == 167) {
            questaoTextView.setText("Qual das alternativas a seguir apresenta, respectivamente, um inibidor da absorção de serotonina, um antidepressivo tricíclico e um inibidor da monoamina oxidase tipo B? ");
            a.setText("Fluoxetina, haloperidol e pralidoxima. ");
            b.setText("Clorpromazina, selegilina e naloxona. ");
            c.setText("Paroxetina, clomipramina e selegilina. ");
            d.setText("Protriptilina, benzotropina e carbidopa. ");
            e.setText("Paroxetina, imipramina e fenobarbital. ");
            alt = R.id.c;
        }
        if (question == 168) {
            questaoTextView.setText("A albumina é a principal proteína sérica, constituindo de 55 a 65% das proteínas plasmáticas e sua dosagem é útil como auxiliar no diagnóstico de diversas condições clínicas importantes. Sobre a albumina e os testes laboratoriais dessa proteína, assinale a alternativa correta. ");
            a.setText("Os rins são os principais produtores de albumina no organismo humano. ");
            b.setText("Em casos de queimadura, os valores plasmáticos de albumina podem estar aumentados em até 4 (quatro) vezes. ");
            c.setText("A dosagem sérica de albumina deve ser realizada sempre que se fizer acompanhamento de pacientes com cirrose hepática. ");
            d.setText("Em pacientes intoxicados por paracetamol, os níveis séricos de albumina podem estar aumentados em até 2 (duas) vezes. ");
            e.setText("Na desidratação severa, os valores plasmáticos da albumina estão mais baixos que o normal. ");
            alt = R.id.c;
        }
        if (question == 169) {
            questaoTextView.setText("Os anti-inflamatórios não esteroidais estão entre os medicamentos mais empregados no tratamento das inflamações agudas e crônicas. Sobre essa classe farmacológica, assinale a alternativa correta. ");
            a.setText("Todos os anti-inflamatórios não esteroidais são medicamentos que têm um efeito de curta duração (< de 6 horas). ");
            b.setText("Praticamente todos os anti-inflamatórios não esteroidais inibem a biossíntese e a liberação de prostaglandinas. ");
            c.setText("Embora tenha efeito anti-inflamatório e antiálgico, o paracetamol é destituído de atividade antitérmica. ");
            d.setText("Muitos anti-inflamatórios não esteroidais apresentam ação antipirética por estimular a biossíntese de prostaglandinas no sistema nervoso central. ");
            e.setText("A fenilbutazona é um anti-inflamatório não esteroidal seletivo da COX2. ");
            alt = R.id.d;
        }
        if (question == 170) {
            questaoTextView.setText("O diabetes melito refere-se a um grupo de distúrbios metabólicos comuns que compartilham o fenótipo da hiperglicemia. Sobre os diversos tipos de diabetes e seu diagnóstico laboratorial, assinale a alternativa INCORRETA. ");
            a.setText("Todos os indivíduos portadores de diabetes melito do tipo 2 não necessitam de insulina para o controle do seu diabetes. ");
            b.setText("O diabetes melito pode resultar de doença exócrina pancreática quando a maioria das ilhotas pancreáticas é destruída. ");
            c.setText("O teste oral de tolerância à glicose avalia a resposta da glicemia a uma carga de carboidrato. ");
            d.setText("A excreção de albumina urinária é importante na avaliação da nefropatia diabética. ");
            e.setText("Quando se realiza o teste oral de tolerância à glicose, a glicemia em jejum também deve ser medida. ");
            alt = R.id.a;
        }
        if (question == 171) {
            questaoTextView.setText("Uma emulsão é uma dispersão em que a fase dispersa é composta de pequenos glóbulos de líquido que se encontram distribuídos em um veículo no qual são imiscíveis. Sobre essas formas farmacêuticas, assinale a alternativa correta. ");
            a.setText("Nas emulsões, a fase dispersa é a fase interna e a fase dispersante é a fase externa. ");
            b.setText("Emulsões apresentando fase interna aquosa e fase externa oleosa são emulsões água em óleo. ");
            c.setText("A viscosidade das emulsões é invariável, sendo a mesma viscosidade para emulsões líquidas e emulsões sólidas. ");
            d.setText("A emulsificação é um processo que visa à preparação de misturas instáveis de dois líquidos miscíveis. ");
            e.setText("Emulsões para aplicação na pele devem ser necessariamente emulsões óleo em água. ");
            alt = R.id.a;
        }
        if (question == 172) {
            questaoTextView.setText("Sobre os distúrbios ácido-básicos e a gasometria arterial, assinale a alternativa correta. ");
            a.setText("Após a coleta do sangue para a gasometria, o contato direto do material com o ar não prejudica a leitura do resultado. ");
            b.setText("Na gasometria, pode ocorrer pseudoacidose quando a incubação de leucócitos metabolicamente ativos produz grandes quantidades de CO2");
            c.setText("Quantidades excessivas de heparina ácida na seringa podem causar uma pseudoalcalose no material a ser analisado na gasometria. ");
            d.setText("A pressão parcial de oxigênio é a mesma para o sangue venoso e para o sangue arterial de um mesmo paciente. ");
            e.setText("A pressão parcial de oxigênio no sangue arterial não é um critério útil para a avaliação do grau de hipoxemia em pacientes internados em UTI. ");
            alt = R.id.b;
        }
        if (question == 173) {
            questaoTextView.setText("Sobre as Boas Práticas para Preparação de Dose Unitária e Unitarização de Doses de Medicamento em Serviços de Saúde, assinale a alternativa INCORRETA. ");
            a.setText("O preparo de doses unitárias e a unitarização de doses de medicamentos, desde que preservadas suas características de qualidade e rastreabilidade, são permitidos exclusivamente às farmácias de atendimento privativo de unidade hospitalar ou qualquer equivalente de assistência médica. ");
            b.setText("A preparação de doses unitárias e a unitarização de doses de medicamentos deve ser realizada sob responsabilidade e orientação do farmacêutico, que deve efetuar os respectivos registros de forma a garantir a rastreabilidade dos produtos e procedimentos realizados. ");
            c.setText("A farmácia deve assegurar a qualidade microbiológica, química e física de todos os medicamentos submetidos à preparação de dose unitária ou unitarização de dose. ");
            d.setText("No caso de fracionamento de medicamentos em serviços de saúde com o rompimento da embalagem primária, o prazo de validade será o determinado previamente pelo fabricante, descrito na embalagem secundária. ");
            e.setText("Os procedimentos para a preparação de dose unitária ou a unitarização de doses de medicamentos devem seguir preceitos farmacotécnicos, de forma a preservar a segurança, eficácia e qualidade do medicamento. ");
            alt = R.id.d;
        }
        if (question == 174) {
            questaoTextView.setText("Os antibióticos betalactâmicos interferem na síntese do peptidoglicano da parede celular bacteriana. Sobre os medicamentos dessa classe, assinale a alternativa correta. ");
            a.setText("O aztreonam é ativo contra bactérias Gram-positivas anaeróbicas. ");
            b.setText("O imipenem é um monobactâmico que não deve ser administrado por via parenteral. ");
            c.setText("Os carbapenens e os monobactâmicos foram desenvolvidos para tratamento das infecções por microrganismos Gram-negativos produtores de betalactamases resistentes às penicilinas. ");
            d.setText("A ceftriaxona é uma penicilina utilizada por via oral para o tratamento de infecções urinárias. ");
            e.setText("A doxiciclina é um antibiótico betalactâmico útil no tratamento de infecções por clamídias. ");
            alt = R.id.c;
        }
        if (question == 175) {
            questaoTextView.setText("A qualidade dos medicamentos, do ponto de vista legal, é resultado do confronto entre as características declaradas pelo fabricante e as que o medicamento realmente apresenta. Assinale a alternativa correta sobre os ensaios farmacopeicos de controle de qualidade de matérias-primas e medicamentos. ");
            a.setText("A cor, o odor e o saber identificam o medicamento, porém não dão informações sobre a sua estabilidade. ");
            b.setText("O teste do volume médio é realizado em soluções estéreis, não sendo recomendada a sua realização em líquidos não estéreis. ");
            c.setText("O teste da velocidade de sedimentação é um teste específico para o controle de qualidade de soluções homogêneas. ");
            d.setText("Nas formas farmacêuticas sólidas, o peso médio, ou determinação de peso, consiste em determinar o peso de vinte unidades individualmente e verificar a percentagem de desvio em relação ao peso médio de comprimidos. ");
            e.setText("A viscosidade dos comprimidos pode ser medida pelas provas de dureza e friabilidade. ");
            alt = R.id.d;
        }
        if (question == 176) {
            questaoTextView.setText("As talassemias constituem um grupo heterogêneo de doenças hereditárias, caracterizadas pela diminuição ou pela ausência de síntese de uma ou mais cadeias globínicas da molécula de hemoglobina. Sobre as talassemias e o metabolismo da hemoglobina, assinale a alternativa INCORRETA. ");
            a.setText("A deficiência de síntese de uma ou mais cadeias polipeptídicas tem duas consequências: síntese diminuída de hemoglobina e desequilíbrio de produção entre as cadeias alfa e não alfa da molécula de hemoglobina. ");
            b.setText("A diminuição da produção da hemoglobina contribui para a anemia e leva à hipocromia característica das talassemias. ");
            c.setText("A gravidade da anemia é determinada pelo grau de desequilíbrio da síntese das cadeias globínicas. ");
            d.setText("Existem portadores do gene talassêmico que são clínica e hematologicamente normais. ");
            e.setText("Nas beta talassemias, não existe produção das cadeias alfa-globínicas. Há excesso de cadeias beta para suprir a falta de cadeias alfa. ");
            alt = R.id.e;
        }
        if (question == 177) {
            questaoTextView.setText("A RDC no4, de 10 de fevereiro de 2009, dispõe sobre as normas de farmacovigilância para os detentores de registro de medicamentos de uso humano. Sobre essa resolução, assinale a alternativa correta. ");
            a.setText("É facultativa aos detentores de registro de medicamentos a designação de um responsável pelas ações de farmacovigilância. ");
            b.setText("Os detentores de registro de medicamentos devem informar, em até 30 dias, aos órgãos de vigilância sanitária as medidas de ação tomadas pela própria empresa em relação aos seus produtos que afetem a segurança do paciente. ");
            c.setText("Os detentores de registro de medicamentos não serão submetidos a inspeções de farmacovigilância por órgão competente do Ministério da Saúde. ");
            d.setText("Os detentores de registro de medicamentos deverão realizar uma autoinspeção de farmacovigilância pelo menos a cada 5 (cinco) anos. ");
            e.setText("As empresas devem possuir Procedimentos Operacionais Padrão para a condução de suas autoinspeções. ");
            alt = R.id.e;
        }
        if (question == 178) {
            questaoTextView.setText("Assinale a alternativa INCORRETA sobre os fármacos utilizados no tratamento das infecções fúngicas. ");
            a.setText("Quando administrada por via oral, a anfotericina é pouco absorvida. ");
            b.setText("O uso da nistatina limita-se ao tratamento de infecções fúngicas da pele e do trato gastrintestinal. ");
            c.setText("O cetoconazol administrado por via oral é eficaz para o tratamento de infecções fúngicas do sistema nervoso central. ");
            d.setText("O fluconazol pode ser administrado tanto por via oral como por via intravenosa. ");
            e.setText("A flucitosina é um agente antifúngico sintético eficaz em infecções fúngicas causadas por leveduras. ");
            alt = R.id.c;
        }
        if (question == 179) {
            questaoTextView.setText("De acordo com a Lei nº 13.021/14, que dispõe sobre o exercício e a fiscalização das atividades farmacêuticas, para o funcionamento das farmácias de qualquer natureza, exigem-se a autorização e o licenciamento da autoridade competente, além das seguintes condições:\n I - Ter a presença de Farmacêutico durante todo o horário de funcionamento, ou, se não for possível, por meio turno somente.\n II - Ter localização conveniente, sob o aspecto sanitário.\n III - Dispor de equipamentos necessários à conservação adequada de imunobiológicos.\n IV - Contar com equipamentos e acessórios que satisfaçam aos requisitos técnicos estabelecidos pela vigilância sanitária.\n Está(ão) CORRETO(S): ");
            a.setText("Somente o item I. ");
            b.setText("Somente os itens II, III e IV. ");
            c.setText("Somente os itens I e II. ");
            d.setText("Somente os itens III e IV. ");
            e.setText("Todos os itens. ");
            alt = R.id.b;
        }
        if (question == 180) {
            questaoTextView.setText("Conforme a Resolução-RDC nº 60/09, que dispõe sobre a produção, dispensação e controle de amostras grátis de medicamentos, assinalar a alternativa INCORRETA: ");
            a.setText("É permitida a distribuição de amostras grátis de preparações magistrais de medicamentos. ");
            b.setText("É vedada a distribuição de amostras grátis de produtos biológicos que necessitem de cuidados especiais de conservação e transporte, conforme registro na Anvisa. ");
            c.setText("Somente é permitida a distribuição de amostras grátis de medicamentos registrados na Anvisa e de apresentações comercializadas pela empresa. ");
            d.setText("A distribuição de amostras grátis de medicamentos somente pode ser feita pelas empresas aos profissionais prescritores. ");
            e.setText("A rotulagem e a bula das amostras grátis de medicamentos devem se apresentar idênticas às aprovadas no registro para a respectiva apresentação do medicamento. ");
            alt = R.id.a;
        }
        if (question == 181) {
            questaoTextView.setText("De acordo com o Código Municipal de Saúde, é o parecer baseado em parâmetros estabelecidos neste Código, normas técnicas especiais, legislação vigente ou em parâmetros de conhecimento técnico internacionalmente reconhecido. Trata-se da definição de: ");
            a.setText("Dispensação. ");
            b.setText("Critério da autoridade sanitária. ");
            c.setText("Autorização. ");
            d.setText("Análise fiscal. ");
            e.setText("Aprovação. ");
            alt = R.id.b;
        }
        if (question == 182) {
            questaoTextView.setText("Considerando-se a prescrição de medicamentos sujeitos a controle especial, assinalar a alternativa CORRETA: ");
            a.setText("As Notificações de Receita A e B são impressas, respectivamente, em papéis de cores azul e amarela. ");
            b.setText("A Notificação de Receita poderá conter até dois produtos farmacêuticos prescritos. ");
            c.setText("Para cada Notificação de Receita A, a quantidade de fármaco prescrita deve corresponder a, no máximo, 60 dias de tratamento. ");
            d.setText("O Receituário de Controle Especial, de cor branca, poderá conter até três produtos farmacêuticos prescritos. ");
            e.setText("O medicamento mazindol deve ser prescrito por meio da Notificação de Receita A. ");
            alt = R.id.d;
        }
        if (question == 183) {
            questaoTextView.setText("Dentre alguns medicamentos que são isentos de ensaio de bioequivalência, desde que sejam equivalentes farmacêuticos ao fármaco de referência, pode-se citar:\n I - Pós para reconstituição que resultem em soluções aquosas orais ou parenterais.\n II - Medicamentos de aplicação tópica, não destinados a efeitos sistêmicos.\n III - Gases.\n Está(ão) CORRETO(S): ");
            a.setText("Somente o item I. ");
            b.setText("Somente os itens I e II. ");
            c.setText("Somente os itens I e III. ");
            d.setText("Somente os itens II e III. ");
            e.setText("Todos os itens. ");
            alt = R.id.e;
        }
        if (question == 184) {
            questaoTextView.setText("Considerando-se a absorção e a distribuição de fármacos, analisar os itens abaixo:\n I - O peso molecular de um fármaco é o principal fator que determina a taxa de difusão passiva através das membranas.\n II - Uma extensa ligação proteica retarda a eliminação de um fármaco.\n III - A absorção no trato gastrointestinal depende do pH em que este se encontra.\n Está(ão) CORRETO(S): ");
            a.setText("Somente o item I. ");
            b.setText("Somente o item III. ");
            c.setText("Somente os itens I e II. ");
            d.setText("Somente os itens II e III. ");
            e.setText("Todos os itens. ");
            alt = R.id.d;
        }
        if (question == 185) {
            questaoTextView.setText("Assinalar a alternativa que apresenta o fármaco hipnótico cuja duração total da ação principal seja ultracurta: ");
            a.setText("Diazepam. ");
            b.setText("Zolpidem. ");
            c.setText("Clonazepam. ");
            d.setText("Flurazepam. ");
            e.setText("Alprazolam. ");
            alt = R.id.b;
        }
        if (question == 186) {
            questaoTextView.setText("Assinalar a alternativa que apresenta o fármaco considerado de escolha para o tratamento de pacientes obesos com diagnóstico de diabetes tipo 2: ");
            a.setText("Metformina. ");
            b.setText("Glibenclamida. ");
            c.setText("Tolbutamida. ");
            d.setText("Acarbose. ");
            e.setText("Insulina. ");
            alt = R.id.a;
        }
        if (question == 187) {
            questaoTextView.setText("Assinalar a alternativa que preenche a lacuna abaixo CORRETAMENTE:\n A _______________, um diurético de alça, deve ser utilizada com cautela devido à sua potente ação que causa uma abundante produção de urina. ");
            a.setText("furosemida ");
            b.setText("amilorida ");
            c.setText("espironolactona ");
            d.setText("hidroclorotiazida ");
            e.setText("clortalidona ");
            alt = R.id.a;
        }
        if (question == 188) {
            questaoTextView.setText("Assinalar a alternativa que apresenta um fármaco com notável ação anti-inflamatória, antipirética e analgésica que apresenta poucos efeitos sobre o trato gastrointestinal, sendo considerado seguro para uso pediátrico: ");
            a.setText("Paracetamol. ");
            b.setText("Ácido acetilsalicílico. ");
            c.setText("Naproxeno. ");
            d.setText("Cetoprofeno. ");
            e.setText("Ibuprofeno. ");
            alt = R.id.e;
        }
        if (question == 189) {
            questaoTextView.setText("Assinalar a alternativa que preenche a lacuna abaixo CORRETAMENTE:\n O fármaco ____________ é um antifúngico que apresenta ação tópica, e seu uso não inclui a administração por via oral. ");
            a.setText("nistatina ");
            b.setText("fluconazol ");
            c.setText("cetoconazol ");
            d.setText("clotrimazol ");
            e.setText("itraconazol ");
            alt = R.id.d;
        }
        if (question == 190) {
            questaoTextView.setText("Dentre os requisitos técnicos que devem ser exigidos em Edital e/ou Contrato de Aquisição de Medicamentos, pode-se citar:\n I - A proposta deverá conter a marca, o fabricante e a procedência do medicamento oferecido.\n II - O número dos lotes deve estar especificado na nota fiscal por quantidade de cada medicamento entregue.\n III - O prazo de validade dos medicamentos não deverá ser inferior a vinte e quatro meses a contar da data da entrega do produto.\n Está(ão) CORRETO(S): ");
            a.setText("Somente o item I. ");
            b.setText("Somente os itens I e II. ");
            c.setText("Somente os itens I e III. ");
            d.setText("Somente os itens II e III. ");
            e.setText("Todos os itens. ");
            alt = R.id.b;
        }
        if (question == 191) {
            questaoTextView.setText("Segundo a Farmacopeia Americana (USP), as classificações de temperaturas de conservação de medicamentos e insumos farmacêuticos são:\n I - Temperatura ambiente: entre 15°C e 30°C.\n II - Local fresco: ambiente cuja temperatura situa-se entre 8°C e 15°C.\n III - Em congelador: temperatura entre 0°C e -20°C.\n Está(ão) CORRETO(S): ");
            a.setText("Somente o item I. ");
            b.setText("Somente os itens I e II. ");
            c.setText("Somente os itens I e III. ");
            d.setText("Somente os itens II e III. ");
            e.setText("Todos os itens. ");
            alt = R.id.e;
        }
        return alt;
    }
}