# missionResourceManager
# Spacecraft Life Support Resource Simulator 
SE 300 Group 6
Kayden Cantu, Abigail Lane, Ryan Miller, Isaac Morrison, Caroline Wenks 

### Problem statement:  

Travel in space is a matter with very many variables but relatively few resources available. Inefficient resource management can lead to critical shortages, endangering the livesy of astronauts during long-duration space missions. Everything that a team of astronauts needs must be present and available on the spacecraft. All consumable resources (food, water, fuel, etc.) will have a rate of expenditure that must be balanced with quantity such that the team does not exhaust all resources before the conclusion of the mission. The inventory, however, must not be a burden to the mission such that the spacecraft becomes inefficient in fuel usage, or must be too large to hold it. Spacecraft that are too large and too heavy become vastly expensive to design, manufacture, launch, maintain, and operate. With these constraints, a balance must be struck between resource quantity and capacity of the spacecraft for the mission to operate efficiently and effectively.  

### Stakeholders:   

Primary Stakeholders: Prof. Keith Garfield, Prof. Christine Walck PhD (Customer) 

Secondary Stakeholders: Aerospace engineers, space operation specialists, astronauts, biomedical engineers, researchers, aerospace physiologists  

### Proposed solution:  

In this project, the focus is on simulating resource consumption rates under various conditions and predicting future resource needs based on different scenarios. The system will notify users of critical resource levels and provide insights based on resulted data for effective resource management. Users will input initial resource levels and mission parameters, enabling the simulation to model resource consumption over time. Multiple scenarios can be run to observe how different factors affect resource levels and generate alerts when levels approach critical thresholds. It is important to note that real-time monitoring of current missions and integration with hardware systems are outside the scope of this project but may be considered outside of the SE 300 class.  

### Proposed implementation method:  

Our initial approach to the solution involves creating a Java-based simulation tool that minimizes user interaction, focusing mainly on data input and running simulations. The system requires historical mission data and resource consumption rates, which will be sourced from existing aerospace research studies. No immediate system integration is required for this program. The program will implement algorithms in Java to simulate resource management, calculate consumption rates, and predict future needs. Some known algorithmic and design solutions that may be implemented include Gaussian predictive model algorithms and Bayesian optimization techniques for predicting and allocating resource levels accurately. 
