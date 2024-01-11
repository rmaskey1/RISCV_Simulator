# RISC-V Simulator

AutoGo is a web-based mock car rental website that emulates the experience of renting a car.

## Table of Contents

- [RISC-V Simulator](#project-title)
  - [Table of Contents](#table-of-contents)
  - [About the Project](#about-the-project)
    - [Built With](#built-with)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Usage](#usage)
  - [Roadmap](#roadmap)
  - [Contributing](#contributing)
  - [License](#license)
  - [Contact](#contact)
  - [Acknowledgements](#acknowledgements)

## About the Project

This project recreates the inner workings of a CPU's system-level processing in a small scale. The RISC-V Simulator is able to read files of machine code for both instructions and data, compile and process them as RISC-V instructions, and execute various functions on the code.

### Built With

This project was built purely with Java.

## Getting Started

This application is a standalone application that anyone can use on their machine. Below, we will go over the steps for using this program on your machine.

### Prerequisites

Below are the necessary prerequisites in order to run the website locally:

- JDK 17 or higher

### Installation

Step-by-step instructions on how to install the project.

1. Clone the repo

   ```sh
   git clone https://github.com/rmaskey1/RISCV_Simulator
   ```

### Usage

Firstly, you will have to choose the sample machine code file you want to run (which are already provided in the "tests" folder).

If there is predefined data that goes with the code, enter that file name next

Once the machine code is loaded and compiled by the program, you can execute the following functions/commands:
1. 'r': runs the entire program in one go till it hits a breakpoint or exits
2. 's': runs the next instruction and then stops and waits for next command
3. 'x0' to 'x31': return the contents of the register from the register file (x0 must always
stay 0)
4. '0x12345678' returns the contents from the address 0x12345678 in the data memory (applies for all addresses)
This should work for all 32 bit addresses, the value shown above is an example
5. 'pc': returns the value of the program counter
6. 'ins': prints the "assembly of the instruction" that will be executed next
7. 'b \[pc]': puts a breakpoint at a particular/ specified \[pc]. So, if 'r' is given as the next
command, the code will run till \pc] (or the end, if \[pc] is not hit) and then break there
8. 'c': continues the execution until it hits the next breakpoint pc or exits

### Roadmap

The following improvements below will make AutoGo more accurate to existing car rental service websites as well as improve the user's experience while navigating the website:

- Car Sorting Filters: In the future, we plan on adding filter to sort cars based on a certain criterion, such as sort by price, number of seats, miles/gallon, etc.
- Email Invoice: We plan on developing an email invoice system for guest users who cannot track their orders through the website. Sending an email invoice to those users will allow them to confirm their order and cancel/return their car through a different method.
- Interactive Map: As of now, the map view of our locations is not interactive. In the future, we plan on adding functionality to the map by letting users choose their pick-up and drop-off location by clicking on the location markers on the map.

### Contributing

To contribute to this project, simply clone the repository and make changes to the code. Make sure to add a commit summary and description for every change made.

### License

This project is licensed under the MIT License - see the LICENSE file for details.

### Contact

Reshaj Maskey: https://github.com/rmaskey1
Quan Mai: https://github.com/dmaighty
Ayush Shresth: https://github.com/ayushshresth021

### Acknowledgements
