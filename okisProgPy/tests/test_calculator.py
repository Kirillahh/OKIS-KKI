import pytest
import sys
import os

sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'src'))

from calculator import Calculator


class TestCalculatorAdvanced:
    """Расширенный тестирующий класс с группировкой тестов"""
    
    def setup_method(self):
        """Настройка перед каждым тестом"""
        self.calc = Calculator()

    # Группа тестов: Базовые арифметические операции
    @pytest.mark.basic_operations
    @pytest.mark.parametrize("a, b, expected", [
        (2, 3, 5),    
        (0, 0, 0),
        (-5, 10, 5),
    ])
    def test_add_parametrized(self, a, b, expected):
        """Тест сложения с различными параметрами - ОШИБКА В ОЖИДАНИИ"""
        assert self.calc.add(a, b) == expected

    @pytest.mark.basic_operations
    @pytest.mark.parametrize("a, b, expected", [
        (10, 5, 5),
        (0, 5, -5),
        (-5, -3, -2),
        (5.5, 2.5, 3.0),
        (100, 100, 0)
    ])
    def test_subtract_parametrized(self, a, b, expected):
        """Тест вычитания с различными параметрами"""
        assert self.calc.subtract(a, b) == expected

    # Группа тестов: Умножение и деление
    @pytest.mark.multiplication_division
    @pytest.mark.parametrize("a, b, expected", [
        (4, 5, 20),
        (0, 100, 0),
        (-3, 4, -12),
        (2.5, 4, 10.0),
        (-2, -3, 6)
    ])
    def test_multiply_parametrized(self, a, b, expected):
        """Тест умножения с различными параметрами"""
        assert self.calc.multiply(a, b) == expected

    @pytest.mark.multiplication_division  
    @pytest.mark.parametrize("a, b, expected", [
        (10, 2, 5),   # Должно быть 5, а не 4
        (5, 2, 2.5),
    ])
    def test_divide_parametrized(self, a, b, expected):
        """Тест деления с различными параметрами - ОШИБКА В ОЖИДАНИИ"""
        assert self.calc.divide(a, b) == expected

    # Группа тестов: Специальные операции
    @pytest.mark.special_operations
    @pytest.mark.parametrize("base, exponent, expected", [
        (2, 3, 8),
        (5, 0, 1),
        (4, 0.5, 2),
        (10, 1, 10),
        (3, 2, 9)
    ])
    def test_power_parametrized(self, base, exponent, expected):
        """Тест возведения в степень с различными параметрами"""
        assert self.calc.power(base, exponent) == expected

    @pytest.mark.special_operations
    @pytest.mark.parametrize("n, expected", [
        (0, 1),
        (1, 1),
        (5, 120),
        (3, 6),
        (4, 24)
    ])
    def test_factorial_parametrized(self, n, expected):
        """Тест факториала с различными параметрами"""
        assert self.calc.factorial(n) == expected

    # Группа тестов: Исключения (более 2 тестов)
    @pytest.mark.exceptions
    def test_divide_by_zero_exception(self):
        """Тест исключения при делении на ноль"""
        with pytest.raises(ValueError, match="Деление на ноль невозможно"):
            self.calc.divide(10, 0)

    @pytest.mark.exceptions
    def test_divide_by_zero_exception_negative(self):
        """Тест исключения при делении на ноль с отрицательным числом"""
        with pytest.raises(ValueError, match="Деление на ноль невозможно"):
            self.calc.divide(-5, 0)

    @pytest.mark.exceptions
    def test_factorial_negative_exception(self):
        """Тест исключения при вычислении факториала отрицательного числа"""
        with pytest.raises(ValueError, match="Факториал определен только для неотрицательных чисел"):
            self.calc.factorial(-5)

    @pytest.mark.exceptions
    def test_factorial_negative_large_exception(self):
        """Тест исключения при вычислении факториала большого отрицательного числа"""
        with pytest.raises(ValueError, match="Факториал определен только для неотрицательных чисел"):
            self.calc.factorial(-10)

    # Группа тестов: История операций
    @pytest.mark.history
    def test_history_after_operations(self):
        """Тест истории после выполнения операций"""
        self.calc.add(2, 3)
        self.calc.multiply(4, 5)
        self.calc.power(2, 3)
        
        history = self.calc.get_history()
        assert len(history) == 3
        assert "2 + 3 = 5" in history
        assert "4 * 5 = 20" in history
        assert "2 ^ 3 = 8" in history

    @pytest.mark.history
    def test_clear_history(self):
        """Тест очистки истории"""
        self.calc.add(1, 2)
        self.calc.subtract(5, 3)
        
        assert len(self.calc.get_history()) == 2
        
        self.calc.clear_history()
        assert len(self.calc.get_history()) == 0

    # Группа тестов: Комплексные сценарии
    @pytest.mark.complex_scenarios
    def test_multiple_operations_sequence(self):
        """Тест последовательности операций"""
        result1 = self.calc.add(10, 5)  # 15
        result2 = self.calc.multiply(result1, 2)  # 30
        result3 = self.calc.subtract(result2, 10)  # 20
        result4 = self.calc.divide(result3, 4)  # 5
        
        assert result4 == 5
        assert len(self.calc.get_history()) == 4

    @pytest.mark.complex_scenarios
    def test_operations_with_zero(self):
        """Тест операций с нулем"""
        assert self.calc.add(0, 0) == 0
        assert self.calc.subtract(0, 5) == -5
        assert self.calc.multiply(0, 100) == 0
        assert self.calc.divide(0, 5) == 0
        assert self.calc.power(0, 5) == 0
        assert self.calc.power(5, 0) == 1


# Дополнительные тесты для демонстрации маркеров
@pytest.mark.basic_operations  
def test_smoke_basic_operations():
    """Дымовой тест базовых операций"""
    calc = Calculator()
    assert calc.add(2, 2) == 4
    assert calc.multiply(3, 3) == 9


@pytest.mark.regression  
def test_regression_division():
    """Регрессионный тест деления"""
    calc = Calculator()
    assert calc.divide(10, 2) == 5
    with pytest.raises(ValueError):
        calc.divide(5, 0)