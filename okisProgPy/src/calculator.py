class Calculator:
    """
    Класс калькулятора с основными математическими операциями
    """
    
    def __init__(self):
        self.history = []
    
    def add(self, a: float, b: float) -> float:
        """Сложение двух чисел"""
        result = a + b
        self.history.append(f"{a} + {b} = {result}")
        return result
    
    def subtract(self, a: float, b: float) -> float:
        """Вычитание двух чисел"""
        result = a - b
        self.history.append(f"{a} - {b} = {result}")
        return result
    
    def multiply(self, a: float, b: float) -> float:
        """Умножение двух чисел"""
        result = a * b
        self.history.append(f"{a} * {b} = {result}")
        return result
    
    def divide(self, a: float, b: float) -> float:
        """Деление двух чисел"""
        if b == 0:
            raise ValueError("Деление на ноль невозможно")
        result = a / b
        self.history.append(f"{a} / {b} = {result}")
        return result
    
    def power(self, base: float, exponent: float) -> float:
        """Возведение в степень"""
        result = base ** exponent
        self.history.append(f"{base} ^ {exponent} = {result}")
        return result
    
    def get_history(self) -> list:
        """Получить историю операций"""
        return self.history
    
    def clear_history(self):
        """Очистить историю операций"""
        self.history.clear()
    
    def factorial(self, n: int) -> int:
        """Вычисление факториала числа"""
        if n < 0:
            raise ValueError("Факториал определен только для неотрицательных чисел")
        if n == 0 or n == 1:
            return 1
        
        result = 1
        for i in range(2, n + 1):
            result *= i
        
        self.history.append(f"{n}! = {result}")
        return result