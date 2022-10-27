# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Badr-Unnisa Bibi, s362110, s362110@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å lime inn Programkode 5.2.3 a). Det blir brukt to nodereferanser p og q i koden hvor q starter i rotnoden. Jeg utfører sammenligningen ved bruk av compare-metoden til komparatoren comp. Nodereferansen q er forelderen til p, dvs. ett nivå over p. q er den siste noden som blir passert når p blir lik null, og det skal dermed legges inn et barn til q. Compare-metoden returnerte den siste verdien som forteller om det er høyre eller venstre barn.

I oppgave 2 så returnerer jeg antall forekomster av en verdi i treet ved hjelp av while-løkke og if-setninger. Returnerer 0 dersom det er null verdi i treet. Returnerer antall forekomster av verdi i treet helt på slutten.

I oppgave 3 gikk jeg frem ved å skrive av Programkode 5.1.7 h) fra kompendiet og redigerte litt på koden. Bruker if-setning for å kaste et unntak dersom treet er tomt. Bruker i tillegg while-løkke og if-setninger for å returnere første node postorden med p som rot. I metoden nestePostorden kaster jeg igjen et unntak dersom treet er tomt (null). Metoden returnerer null dersom p er den siste i postorden. Bruker if-setninger inni hverandre for å returnere den noden som kommer etter p i postorden.

I oppgave 4 bruker jeg funksjonen nestePostorden fra forrige oppgave i denne oppgaven. Finner den første noden p i postorden, og lager deretter en while-løkke hvor jeg tar i bruk setningen fra oppgaven, ```p = nestePostorden(p);```.

I oppgave 5 bruker jeg metoden serialize og en kø til å traversere treet i nivå orden. Arrayet til funksjonen serialize inneholder verdiene i alle nodene i vår orden. Den andre funksjonen deserialize tar imot det arrayet, og legger inn alle verdiene omigjen i nivå orden for å kunne gjenskape treet som jeg har kalt for tre2.

I oppgave 6 kopierer jeg Programkode 5.2.8 d). (I denne programkoden er det slik at hvis en verdi forekommer flere ganger vil kun den første forekomsten bli fjernet.)
- I funksjonen fjern: Bruker if-setning til å returnere false dersom treet har ingen nullverdier. Bruker også her compare-metoden for å sammenligne. Reduserer antall noder i treet, og øker endringer for hver gang.
- I funksjonen fjernAlle: Bruker if-setning for sjekke om treet ikke er tomt, og fortsetter med en while-løkke for å returnere antallet forekomster som ble fjernet.
- I funksjonen nullstill: Nullstiller pekere og nodeverdier i treet. Setter rot til null og antall til 0. I tillegg setter endringer til 0 og nullstiller rot.
