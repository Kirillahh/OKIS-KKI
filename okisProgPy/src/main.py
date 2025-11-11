from calculator import Calculator

def main():
    calc = Calculator()
    
    print("Добро пожаловать в калькулятор!")
    print("Доступные операции: +, -, *, /, ^, !, history, clear, exit")
    
    while True:
        try:
            command = input("\nВведите команду: ").strip().lower()
            
            if command == 'exit':
                print("До свидания!")
                break
            elif command == 'history':
                history = calc.get_history()
                if history:
                    print("История операций:")
                    for operation in history:
                        print(f"  {operation}")
                else:
                    print("История пуста")
            elif command == 'clear':
                calc.clear_history()
                print("История очищена")
            elif command in ['+', '-', '*', '/', '^']:
                a = float(input("Введите первое число: "))
                b = float(input("Введите второе число: "))
                
                if command == '+':
                    result = calc.add(a, b)
                elif command == '-':
                    result = calc.subtract(a, b)
                elif command == '*':
                    result = calc.multiply(a, b)
                elif command == '/':
                    result = calc.divide(a, b)
                elif command == '^':
                    result = calc.power(a, b)
                
                print(f"Результат: {result}")
                
            elif command == '!':
                n = int(input("Введите число для вычисления факториала: "))
                result = calc.factorial(n)
                print(f"Результат: {result}")
                
            else:
                print("Неизвестная команда")
                
        except ValueError as e:
            print(f"Ошибка: {e}")
        except Exception as e:
            print(f"Произошла ошибка: {e}")

if __name__ == "__main__":
    main()