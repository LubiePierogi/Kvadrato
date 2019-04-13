# Kvadrato

Gra zręcznościowa polegająca na unikaniu przeszkód.

## Menu

Po uruchomieniu gry pojawia się duży kwadrat, po którego kliknięciu rozwija się
menu składające się z kilku przycisków:

* Graj - rozpoczyna rozgrywkę.
* Pomoc - pokazuje ekran, w którym wyjaśnione jest sterowanie.
* Wyjście - wyłącza grę.

Menu można obsługiwać również za pomocą klawiatury, używając strzałek, spacji,
entera i escape.

## Rozgrywka

W grze dostajemy kontrolę nad małym kwadratem, który jest ciągniony po prostym
torze. Na tym torze pojawiają się różne przeszkody. Celem gracza jest unikanie
tych przeszkód lub ustawianie koloru tła pasującego do przeszkód, w które wpada.
Gracz steruje kwadratem za pomocą strzałek, a zmienia kolor wciskając litery
odpowiednio:

* Q - ciemnoniebieski,
* W - zielony,
* E - żółty,
* R - jasnoniebieski,
* A - purpurowy,
* S - czerwony
* D - brązowy,
* F - pomarańczowy.

Po uderzeniu w przeszkodę bez odpowiedniego koloru gracz nie przegrywa
natychmiast, ale ma bardzo krótki czas, żeby “wyjść” z przeszkody. Jeśli tego
nie zrobi, gra się kończy i ukazuje się ekran z ilością zdobytych punktów.

## Szczegóły techniczne

Gra jest napisana w Javie i jest zgodna z Javą 8. Do wyświetlania grafiki używa
biblioteki JavaFX. Wykorzystuje wzorzec MVC.

## Kompilacja

Do kompilowania projekt używa programu Ant. Dostępne polecenia:

* `ant compile​` - skompiluje klasy i umieści je w katalogu build,
* `ant run​` - skompiluje i uruchomi program
* `ant compileTest​` - skompiluje testy jednostkowe
* `ant test​` - skompiluje i uruchomi testy jednostkowe
* `ant jar​` - stworzy plik Kvadrato.jar z całą grą
* `ant clean​` - usunie wszystkie pliki niebędące źródłowymi
* `ant roll`​ - wykona po kolei polecenia ​`clean`, `compile​`, `run`
