## Remasp Edit

Dieses Repo enthält Code für eine editierte Version von [Remasp][remasp]. Remasp ist ein Emulator für eine Registermaschine welche für den Unterricht in der Oberstufe in hessischen Schulen genutzt wird. Die Remasp Originalversion wurde von Norman Sutatyo entwickelt. (Welcher leider keine verlinkbare Internetpräsenz hat).

## Funktionsweiße
 
Dieser Mod erweitertert die Standardfunktionsweiße, wie auf der hessischen Website beschrieben um folgende Aspekte: 

### Negative Zahlen.
 
Negative Zahlen können auf einer Per-Maschinen-Basis mit Hilfe einer Instruktion aktiviert werden. Desweiteren wurden einige Bugs die in der normalen Version das Arbeiten mit negativen Zahlen erlaubt haben ausgebessert.

 - Die Instruktion ``ALLOWNEG``  aktiviert negative Zahlen für den Rest des Programms
 - Die Instruktion ``JNEG`` ist ein konditionaler Jump, der springt, falls der Akummulator negativ ist.

### Mehr Schriftgrößen.

Wenn das Original Remasp für den Frontunterricht genutzt wurde, kam es in unserem Unterricht häufiger zu Problemen der Lesbarkeit in hinteren Reihen. Eine weitere Schriftgrößenoption soll dies verbesseren.

### Verbesserte Register

Die Register zeigen jetzt das korrespondierende ASCII-Zeichen an.

[remasp]: https://arbeitsplattform.bildung.hessen.de/fach/informatik/registermaschine.html