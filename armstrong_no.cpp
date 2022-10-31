#include <iostream>
#include <cmath>
using namespace std;

int main() {

  int num1, num2, i, num, digit, sum, count;

  cout << "Enter first number: ";
  cin >> num1;

  cout << "Enter second number: ";
  cin >> num2;

  // swap if num1 > num2
  if (num1 > num2) {
    num1 = num1 + num2;
    num2 = num1 - num2;
    num1 = num1 - num2;
  }
  
  cout << "Armstrong numbers between " << num1 << " and " << num2 << " are: " << endl;
 
  // print Armstrong numbers from num1 to num2
  for(i = num1; i <= num2; i++) {

    // count gives the number of digits in i
    count = 0;
         
