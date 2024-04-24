# Bitcoin API



---

## Estimation du Hashrate



La classe `HashRateEstimator` est conçue pour estimer le nombre de hachages SHA-256 qu'une machine peut réaliser par seconde, ce qui est essentiel dans le contexte du minage de cryptomonnaies comme Bitcoin.

### Fonctionnement

- **Initialisation :** La classe prend deux paramètres : `duration` (durée de chaque test en millisecondes) et `numberOfTries` (nombre de tests à effectuer).

- **Processus d'Estimation :** La méthode `estimate()` effectue le hachage SHA-256 sur un tableau de 32 octets pendant la `duration` spécifiée, cela est répété `numberOfTries` fois pour calculer une moyenne.

- **Mesure :** Un tableau de 32 octets est haché dans chaque essai pour simuler le hachage d'un bloc de transactions Bitcoin, avec des données aléatoires générées pour chaque hachage.

- **Calcul du Hashrate :** Le hashrate est estimé en divisant le nombre total de hachages réalisés par la durée totale des essais en secondes.

### Résultat

Pour un total de 5 essais, chacun d'une durée de 5000 millisecondes (5 secondes), le hashrate estimé était de **3122366.12 hachages par seconde** (machine personnelle). Cela indique une capacité de plus de 3 millions de hachages SHA-256 par seconde pour la machine testée.


## Récupération et Analyse d'un Bloc Récent de la Blockchain Bitcoin

Cette section détaille le processus de récupération du dernier bloc de la blockchain Bitcoin et l'analyse de certaines de ses propriétés clés.

### Processus

Le processus implique plusieurs étapes clés pour récupérer des informations sur le dernier bloc :

1. **Récupération du Dernier Hash de Bloc :** À l'aide de l'API, le hash du dernier bloc a été récupéré.
    - **Dernier Hash de Bloc :** `00000000000000000001002fe0dcfe6620c295c1a368924f81495aefb0312ae1`

2. **Récupération des Données Brutes du Bloc :** Les données brutes du bloc correspondant à ce hash ont ensuite été récupérées, nous permettant d'analyser la taille des données du bloc.
    - **Longueur des Données Brutes :** `1873001` octets

3. **Analyse du Bloc :** Après avoir récupéré le bloc à l'aide de son hash, plusieurs propriétés clés ont été analysées et affichées.
    - **Hash du Bloc :** `00000000000000000001002fe0dcfe6620c295c1a368924f81495aefb0312ae1`
    - **Version :** `872202240`
    - **Hash du Bloc Précédent :** `000000000000000000003944485a5f24937d84f09e6fc79b055ef0b9ea243011`
    - **Racine de Merkle :** `7ebbd5170aef15fc371710fb9bce44751da06886a17ca4f9c9a3708b663069d3`
    - **Horodatage (Timestamp) :** `1711225412`
    - **Nonce :** `1387856993`
    - **Nombre de Transactions :** `2874`

### Observations

- Le bloc récupéré contient `2874` transactions, ce qui indique une activité transactionnelle relativement élevée.
- L'horodatage indique le moment de la dernière confirmation du bloc.
- La présence d'un grand nombre de transactions et les détails spécifiques du bloc (comme la racine de Merkle et le nonce) sont essentiels pour comprendre le fonctionnement de la blockchain Bitcoin.

Ces informations offrent une fenêtre sur l'état actuel et le fonctionnement de la blockchain Bitcoin, démontrant la complexité et la robustesse du réseau.


## Analyse de la Première Transaction du Bloc

La première transaction de chaque bloc Bitcoin, connue sous le nom de transaction "coinbase", est unique car elle récompense le mineur qui a réussi à miner le bloc. Voici les détails de la première transaction du bloc analysé :

- **Hash de la Transaction :** `80fd823886b09e5a1c8bf67ed2de216a49c5ef4162c6839cddc39a356b0d6923`
- **Nombre d'Entrées :** `1`
- **Nombre de Sorties :** `2`
- **Valeurs des Sorties :**
    - **Sortie 1 :** `6.36956659 BTC`, adressée à `bc1qxhmdufsvnuaaaer4ynz88fspdsxq2h9e9cetdj`
    - **Sortie 2 :** `0.00 BTC`, l'adresse n'a pas pu être décodée.

### Observations :

- La transaction coinbase inclut une entrée marquée "COINBASE", indiquant qu'il s'agit bien de la récompense de minage.
- La première sortie transfère la récompense de bloc et les éventuels frais de transaction au mineur. La valeur spécifique de `6.36956659 BTC` montre la récompense de bloc actuelle plus les frais des transactions incluses dans ce bloc.
- La seconde sortie, avec une valeur de `0.00 BTC`, est souvent utilisée pour insérer des données arbitraires dans la blockchain, comme des messages ou des votes pour des propositions de changement du protocole (BIPs).

Concernant la première transaction d'un bloc, voici ce que l'on constate généralement : 
- C'est une Transaction Coinbase : La première transaction d'un bloc, connue sous le nom de transaction 
coinbase, est spéciale. Elle n'a pas d'entrées venant d'autres transactions. Au lieu de cela, elle crée de
nouveaux bitcoins en tant que récompense de bloc pour le mineur. Cela signifie que les bitcoins de cette
transaction sont créés à partir de rien, en respectant la limite d'offre monétaire programmée de Bitcoin.


## Minage d'un Nouveau Bloc

Dans cette étape, nous avons simulé le processus de minage d'un nouveau bloc en utilisant une classe `Miner` personnalisée. Voici les étapes clés de la procédure :

1. **Initialisation du Mineur :** Nous avons instancié un objet `Miner` avec les paramètres de réseau actuels.

2. **Préparation des Transactions :** Pour cet exemple, nous avons utilisé une liste vide de transactions, simulant ainsi un bloc contenant uniquement la transaction coinbase.

3. **Génération de la Clé du Mineur :** Une nouvelle paire de clés ECDSA a été générée pour simuler l'adresse du mineur recevant la récompense du bloc.

4. **Récupération du Dernier Bloc :** Le hash du dernier bloc a été utilisé pour obtenir les détails du bloc précédent, nécessaire pour construire le nouveau bloc.

5. **Processus de Minage :** Le bloc a été miné en cherchant un nonce valide qui satisfait la condition de difficulté actuelle.

### Résultat du Minage

- Le processus de minage a réussi à produire un nouveau bloc valide.
- **Hash du Nouveau Bloc :** `7939e50b6c8c16cca28afb1ed15b8bab8ba14dd1f004418bd0267956119b19f9`


## Acceptation du Bloc Simulé par le Réseau

La question clé est de savoir si le bloc que nous avons miné dans le cadre de cette simulation serait accepté par le réseau Bitcoin réel. La réponse courte est **non**, et voici les principales raisons :

### Raisons de Non-Acceptation

1. **Difficulté de Minage :** La difficulté de minage dans notre simulation est significativement plus basse que celle du réseau Bitcoin réel. Le réseau ajuste la difficulté pour maintenir un temps moyen de 10 minutes par bloc. Notre simulation, en utilisant une difficulté réduite, ne répond pas à ces critères stricts.

2. **Validation des Transactions :** Le réseau vérifie minutieusement chaque transaction dans un bloc pour s'assurer qu'elles sont toutes valides. Un bloc contenant des transactions non valides ou fictives serait immédiatement rejeté.

3. **Consensus de la Chaîne :** Même si le bloc était miné avec une difficulté conforme et contenait uniquement des transactions valides, il doit être correctement relié à la chaîne de blocs existante. Le bloc doit référencer le hash du dernier bloc accepté par le consensus du réseau.

4. **Règles du Protocole :** Le réseau Bitcoin opère selon un ensemble strict de règles codées dans le protocole. Tout bloc qui ne respecte pas ces règles, par exemple en ayant une structure incorrecte ou en attribuant une récompense de bloc incorrecte, serait rejeté.

### Conclusion

Bien que notre simulation de minage démontre le concept de base du minage de blocs et de la sécurisation de la blockchain, elle est grandement simplifiée et ne représente pas fidèlement le processus complexe et hautement compétitif de minage sur le réseau Bitcoin réel. Le minage réussi sur le réseau réel nécessite non seulement une puissance de calcul considérable mais aussi une stricte adhérence aux règles du protocole Bitcoin.


## Temps Moyen Pour Miner un Bloc

Nous avons abordé le calcul du temps moyen nécessaire pour miner un bloc en prenant en compte le hashrate individuel d'un mineur et la difficulté actuelle du réseau Bitcoin. Cette analyse est cruciale pour comprendre l'efficacité et la viabilité du minage sous différentes conditions de réseau.

### Explication du Code

La méthode centrale de notre analyse est `expectedMiningTime`, située dans la classe `ImpactUtils`. Cette méthode est conçue pour estimer le nombre de secondes nécessaires en moyenne pour qu'un mineur trouve un nonce valide qui satisfait la condition de difficulté du réseau.

```java
public static long expectedMiningTime(long hashrate, BigInteger difficultyAsInteger) {
    // Convertit le hashrate en BigInteger pour permettre des opérations avec la difficulté
    BigInteger hashrateBI = BigInteger.valueOf(hashrate);
    // Calcule le nombre de secondes attendues en divisant la difficulté par le hashrate
    BigInteger seconds = difficultyAsInteger.divide(hashrateBI);
    // Convertit le résultat en long pour retourner une valeur utilisable
    return seconds.longValue();
} 
```
## Temps Moyen Pour Miner un Bloc Sur Votre Machine

En utilisant le hashrate déterminé lors de la question 1, nous allons calculer le nombre moyen d'années nécessaires pour miner un bloc en conditions réelles sur votre machine.

### Détails du Calcul

- **Hashrate (Q1) :** 0.00312236612 GH/s
- **Difficulté (utilisée pour le calcul) :** 522,190,847 (valeur simplifiée pour l'exemple)

Nous avons déjà calculé le temps moyen pour miner un bloc avec ces paramètres à la Q6, qui était de 167 secondes.

### Conversion en Années

Pour convertir le temps de minage d'un bloc de secondes en années, nous utilisons la formule suivante :

```math
Temps en années = (Temps en secondes) / (60 * 60 * 24 * 365)

En insérant notre valeur de 167 secondes :

Temps en années = 167 / (60 * 60 * 24 * 365) ≈ 0.00000529 années

Conclusion

Avec le hashrate déterminé lors de la Q1 et une difficulté simplifiée, le temps moyen nécessaire pour miner un seul bloc
sur notre machine (personnelle) serait d'environ 0.00000529 années, ce qui équivaut à environ 0.00193 jours, ou environ 2.77 minutes.
### Paramètres :

- **hashrate :** La capacité de hachage du mineur exprimée en hashes par seconde (H/s).
- **difficultyAsInteger :** La difficulté actuelle du réseau exprimée comme un BigInteger, permettant des calculs précis sur des valeurs très grandes.
```
```java 
public static double expectedYearsToMineABlock(long hashrate, BigInteger difficultyAsInteger) {
        long seconds = expectedMiningTime(hashrate, difficultyAsInteger);
        return seconds / (60.0 * 60 * 24 * 365.25);
    }
```
### Fonctionnement :

Le hashrate est d'abord converti en BigInteger pour assurer la compatibilité avec la difficulté qui est également 
exprimée sous cette forme. Le nombre moyen de secondes nécessaire est calculé en divisant la difficulté par le hashrate.
Ce calcul repose sur l'idée que le temps nécessaire pour trouver un nonce valide est proportionnel à la difficulté et 
inversement proportionnel au hashrate. Finalement, le résultat est converti de BigInteger en long, fournissant une 
estimation du temps en secondes.

### Résultats Obtenus :
Avec un hashrate de 0.00312236612 GH/s, et une difficulté de réseau de 522190847, le temps moyen pour miner un bloc est de 167 secondes.

Cette estimation met en lumière le lien direct entre la difficulté du réseau et l'efficacité du minage. Avec un hashrate
relativement modeste par rapport aux standards actuels du réseau Bitcoin, et une difficulté artificiellement basse 
(pour les besoins de notre TP), il faudrait en moyenne 167 secondes pour miner un bloc. Cette durée est 
significativement plus courte que celle observée sur le réseau réel, soulignant l'impact colossal de la difficulté sur 
le processus de minage.

## Hashrate Actuel de l'Ensemble du Réseau

Dans le cadre de notre étude sur l'impact énergétique et le processus de minage de Bitcoin, une des clés pour comprendre la compétitivité et la viabilité du minage est de connaître le hashrate global du réseau. Le hashrate du réseau reflète la puissance de calcul totale déployée par tous les mineurs dans le but de sécuriser le réseau et de miner de nouveaux blocs.

### Calcul du Hashrate à partir de la Difficulté Simplifiée

En utilisant la difficulté simplifiée (`EASY_DIFFICULTY_TARGET` ou une valeur équivalente utilisée pour les besoins de notre simulation), nous avons calculé une estimation du hashrate global du réseau Bitcoin :

- **Difficulté Utilisée pour la Simulation :** `EASY_DIFFICULTY_TARGET`
- **Hashrate Estimé du Réseau :** 3737987.683559232 GH/s

Cette valeur représente la somme des efforts de minage déployés par les mineurs à travers le monde, sous l'hypothèse simplifiée de notre difficulté.

### Implications

Avec un hashrate global estimé à près de 3.74 millions de GH/s, cette valeur souligne l'immense quantité d'énergie et de puissance de calcul consacrée au minage de Bitcoin. Cela montre également la robustesse et la sécurité du réseau, car une telle puissance de calcul rend les attaques par force brute extrêmement difficiles et coûteuses.

Il est important de noter que cette estimation est basée sur une difficulté simplifiée utilisée pour notre TP. Dans la réalité, le hashrate du réseau Bitcoin peut être significativement plus élevé, reflétant les conditions actuelles de minage et la participation des mineurs.

Cette analyse démontre l'importance de la puissance de calcul dans l'écosystème Bitcoin et met en perspective les défis et les enjeux associés au minage de nouveaux blocs.


## Calcul de l'Énergie Consommée par le Réseau Bitcoin

Pour évaluer l'impact environnemental du minage de Bitcoin, nous avons développé une méthode permettant d'estimer la consommation énergétique totale du réseau sur une période de 24 heures. Cette estimation repose sur l'hypothèse simplifiée que tous les mineurs utilisent le même type d'équipement de minage, avec une capacité et une consommation d'énergie spécifiques.

### Méthode de Calcul

La méthode `calculateEnergyConsumed` de la classe `ImpactUtils` a été conçue pour réaliser cette estimation. Voici un aperçu de son fonctionnement :

- **Entrées :** La capacité de hashage d'un équipement en TH/s et sa consommation d'énergie en Watts.
- **Sortie :** L'énergie consommée par le réseau en kWh sur les dernières 24 heures.

### Test de la Méthode

Pour tester cette méthode, nous avons choisi le Bitmain Antminer S19 Pro comme référence, avec les caractéristiques suivantes :

- **Capacité de Hashage :** 110 TH/s
- **Consommation d'Énergie :** 3250 Watts

Le test a été effectué dans la classe principale `App` de notre application. Voici le code utilisé pour le test :

```java
public static void main(String[] args) {
    double capacityInTHs = 110; // Capacité en TH/s
    double powerInWatts = 3250; // Puissance en Watts
    
    double energyConsumed = ImpactUtils.calculateEnergyConsumed(capacityInTHs, powerInWatts);
    
    System.out.println("Énergie consommée par le réseau Bitcoin sur 24 heures (en utilisant Bitmain Antminer S19 Pro): "
            + energyConsumed + " kWh");
}
```
### Résultats

En exécutant ce test, nous obtenons l'estimation suivante pour la consommation énergétique du réseau Bitcoin sur 24 heures, en supposant que tous les mineurs utilisent le Bitmain Antminer S19 Pro :

    Énergie Consommée : 78.0 kWh

## Estimation du Coût d'Opération Quotidien du Réseau Bitcoin

Dans cette section, nous explorons le coût d'opération quotidien associé à deux scénarios distincts de minage Bitcoin :
l'utilisation de l'équipement le plus efficace du point de vue énergétique et l'utilisation des équipements plus anciens
mais toujours rentables.

### Scénarios d'Analyse

- **Hypothèse Basse (Équipement le Plus Efficace) :** Dans ce scénario, nous considérons que tous les mineurs utilisent 
le matériel de minage le plus efficace disponible, maximisant ainsi la capacité de hashage tout en minimisant la 
consommation d'énergie.

- **Hypothèse Haute (Plus Anciens Mais Rentables) :** Ici, l'hypothèse est que les mineurs exploitent des équipements 
plus anciens, qui, bien que moins efficaces que les modèles les plus récents, demeurent rentables en raison de leur 
faible consommation d'énergie relative à la valeur générée par le minage.

### Résultats

Les coûts d'opération quotidiens calculés pour chaque scénario sont les suivants :

- **Coût d'Opération Quotidien pour l'Équipement le Plus Efficace :** 12.73 $

  Ce résultat reflète le coût associé à l'utilisation d'équipements hautement performants et efficaces. Bien que ces équipements consomment une quantité significative d'électricité, leur efficacité énergétique et leur capacité de hashage élevée justifient ce coût dans un contexte de minage intensif.

- **Coût d'Opération Quotidien pour l'Équipement Plus Ancien mais Rentable :** 1.42 $

  Ce chiffre met en évidence la viabilité des équipements plus anciens dans le cadre du minage de Bitcoin. Malgré leur moindre efficacité par rapport aux modèles récents, leur faible consommation d'énergie et la rentabilité maintenue les rendent toujours attractifs pour certaines opérations de minage.

### Conclusion

Ces estimations illustrent l'impact significatif de l'efficacité énergétique et de la capacité de hashage sur les coûts d'opération dans le minage de Bitcoin. Elles soulignent l'importance de choisir judicieusement l'équipement de minage pour optimiser la rentabilité, en prenant en compte à la fois la performance et le coût énergétique.

Il est crucial de noter que ces résultats sont basés sur des hypothèses simplifiées et ne reflètent pas nécessairement la complexité ni la diversité des opérations de minage réelles. La sélection d'équipements spécifiques et le prix de l'électricité peuvent varier considérablement, influençant directement la rentabilité du minage Bitcoin.

## Q11 : Comparaison de la Consommation Énergétique avec la Finlande

Pour comparer la consommation énergétique estimée du réseau Bitcoin avec celle d'un pays entier, nous avons utilisé l'API open data de Fingrid, le gestionnaire du réseau de transport d'électricité en Finlande. Voici comment nous avons procédé :

### FingridAPI.java

Nous avons créé la classe `FingridAPI` pour interroger l'API de Fingrid et récupérer les dernières données disponibles sur la consommation d'énergie en Finlande.
Résultats

Après avoir exécuté le script, nous avons obtenu une réponse 200 OK de l'API, indiquant une récupération réussie des données. La consommation énergétique pour la période spécifiée a été affichée, permettant une comparaison directe avec nos estimations pour le réseau Bitcoin.

Cette approche nous a permis d'évaluer l'impact énergétique du minage de Bitcoin en le comparant à la consommation d'énergie d'un pays entier, soulignant l'importance des discussions sur la durabilité et l'efficacité énergétique dans la technologie blockchain.

---

## Méthodes et Outils Utilisés

- Version Java : Java 21
- Gradle : 7.2
- Dépendances : BitcoinJ, SLF4J, JSON, logback...

## Licence 

Ce projet est sous licence Apache 2.0. Pour plus de détails, veuillez consulter le fichier `LICENSE.md` ou visiter Apache License, Version 2.0.
