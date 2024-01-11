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
  - [Contributing](#contributing)
  - [Contact](#contact)

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
1. 'r': runs the entire program in one go until it hits a breakpoint or exits
2. 's': runs the next instruction and then stops and waits for next command
3. 'x0' to 'x31': return the contents of the register from the register file (x0 must always stay 0)
4. '0x12345678' returns the contents from the address 0x12345678 in the data memory (applies for all addresses, the given is just an example)
5. 'pc': returns the value of the program counter
6. 'ins': prints the "assembly of the instruction" that will be executed next
7. 'b \[pc]': puts a breakpoint at a particular/ specified \[pc]. So, if 'r' is given as the next
command, the code will run till \pc] (or the end, if \[pc] is not hit) and then break there
8. 'c': continues the execution until it hits the next breakpoint pc or exits

### Contributing

To contribute to this project, simply clone the repository and make changes to the code. Make sure to add a commit summary and description for every change made.

### Contact

Reshaj Maskey: https://github.com/rmaskey1
