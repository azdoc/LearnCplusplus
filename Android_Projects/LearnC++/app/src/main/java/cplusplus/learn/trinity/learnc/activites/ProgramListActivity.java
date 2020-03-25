package cplusplus.learn.trinity.learnc.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.adapter.ProgramRecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.model.ProgramModel;
import cplusplus.learn.trinity.learnc.model.TutorialModel;
import cplusplus.learn.trinity.learnc.utilities.ClickListener;
import cplusplus.learn.trinity.learnc.utilities.CommonActivity;
import cplusplus.learn.trinity.learnc.utilities.RecyclerTouchListener;

public class ProgramListActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // custom code
        String programCategory = getIntent().getStringExtra("programCategory");
        final List<ProgramModel> programsList = getProgramsList(programCategory);
        ArrayList<String> mArrayList = new ArrayList<>();
        for (ProgramModel p:programsList){
            mArrayList.add(p.getTitle());
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.program_list_recycler_view);

        final ProgramRecyclerItemsAdapter programRecyclerItemsAdapter = new ProgramRecyclerItemsAdapter(this, mArrayList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(programRecyclerItemsAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(ProgramListActivity.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //showToast(programsList.get(position).getTitle(),ProgramListActivity.this)  ;
                Intent intent = new Intent(getBaseContext(),TutorialDispalyActivity.class);
                TutorialModel tutorialModel = new TutorialModel();
                tutorialModel.setResource(programsList.get(position).getProgram());
                tutorialModel.setTopic(programsList.get(position).getTitle());
                tutorialModel.setToolBarTitle("Programs");
                intent.putExtra("tutorial", tutorialModel);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        EditText inputSearch = (EditText) findViewById(R.id.inputSearch);


    }

    public List<ProgramModel> getProgramsList(String key) {

        // basic programs
        ArrayList<ProgramModel> basicsList = new ArrayList<ProgramModel>();
        basicsList.add(new ProgramModel("\"Hello, World!\" Program", R.string.basic_Hello_World, R.string.basic_Hello_World_op));
        basicsList.add(new ProgramModel("Program to Print Number Entered by User", R.string.basic_Print_Number_Entered_by_User, R.string.basic_Print_Number_Entered_by_User_op));
        basicsList.add(new ProgramModel("Program to Add Two Numbers", R.string.basic_Add_Two_Numbers, R.string.basic_Add_Two_Numbers_op));
        basicsList.add(new ProgramModel("Program to Find Quotient and Remainder", R.string.basic_Find_Quotient_and_Remainder, R.string.basic_Find_Quotient_and_Remainder_op));
        basicsList.add(new ProgramModel("Program to Find Size of int, float, double and char in Your System", R.string.basic_Find_Size_of_int_float_double_and_char_in_Your_System, R.string.basic_Find_Size_of_int_float_double_and_char_in_Your_System_op));
        basicsList.add(new ProgramModel("Program to Swap Two Numbers", R.string.basic_Swap_Two_Numbers, R.string.basic_Swap_Two_Numbers_op));
        basicsList.add(new ProgramModel("Program to Check Whether Number is Even or Odd", R.string.basic_Check_Whether_Number_is_Even_or_Odd, R.string.basic_Check_Whether_Number_is_Even_or_Odd_op));
        basicsList.add(new ProgramModel("Program to Check Whether a character is Vowel or Consonant.", R.string.basic_Check_Whether_a_character_is_Vowel_or_Consonant, R.string.basic_Check_Whether_a_character_is_Vowel_or_Consonant_op));
        basicsList.add(new ProgramModel("Program to Find Largest Number Among Three Numbers", R.string.basic_Find_Largest_Number_Among_Three_Numbers, R.string.basic_Find_Largest_Number_Among_Three_Numbers_op));
        basicsList.add(new ProgramModel("Program to Find All Roots of a Quadratic Equation", R.string.basic_Find_All_Roots_of_a_Quadratic_Equation, R.string.basic_Find_All_Roots_of_a_Quadratic_Equation_op));
        basicsList.add(new ProgramModel("Program to Calculate Sum of Natural Numbers", R.string.basic_Calculate_Sum_of_Natural_Numbers, R.string.basic_Calculate_Sum_of_Natural_Numbers_op));
        basicsList.add(new ProgramModel("Program to Check Leap Year", R.string.basic_Check_Leap_Year, R.string.basic_Check_Leap_Year_op));
        basicsList.add(new ProgramModel("Program to Find Factorial", R.string.basic_Find_Factorial, R.string.basic_Find_Factorial_op));
        basicsList.add(new ProgramModel("Program to Generate Multiplication Table", R.string.basic_Generate_Multiplication_Table, R.string.basic_Generate_Multiplication_Table_op));
        basicsList.add(new ProgramModel("Program to Display Fibonacci Series", R.string.basic_Display_Fibonacci_Series, R.string.basic_Display_Fibonacci_Series_op));
        basicsList.add(new ProgramModel("Program to Find GCD", R.string.basic_Find_GCD, R.string.basic_Find_GCD_op));
        basicsList.add(new ProgramModel("Program to Find LCM", R.string.basic_Find_LCM, R.string.basic_Find_LCM_op));
        basicsList.add(new ProgramModel("Program to Reverse a Number", R.string.basic_Reverse_a_Number, R.string.basic_Reverse_a_Number_op));
        basicsList.add(new ProgramModel("Program to Calculate Power of a Number", R.string.basic_Calculate_Power_of_a_Number, R.string.basic_Calculate_Power_of_a_Number_op));
        basicsList.add(new ProgramModel("Program to Find ASCII Value of a Character", R.string.basic_Find_ASCII_Value_of_a_Character, R.string.basic_Find_ASCII_Value_of_a_Character_op));
        basicsList.add(new ProgramModel("Program to Multiply two Numbers", R.string.basic_Multiply_two_Numbers, R.string.basic_Multiply_two_Numbers_op));
        basicsList.add(new ProgramModel("Program to Check Whether a Number is Palindrome or Not", R.string.basic_Check_Whether_a_Number_is_Palindrome_or_Not, R.string.basic_Check_Whether_a_Number_is_Palindrome_or_Not_op));
        basicsList.add(new ProgramModel("Program to Check Whether a Number is Prime or Not", R.string.basic_Check_Whether_a_Number_is_Prime_or_Not, R.string.basic_Check_Whether_a_Number_is_Prime_or_Not_op));
        basicsList.add(new ProgramModel("Program to Display Prime Numbers Between Two Intervals", R.string.basic_Prime_Numbers_Between_Two_Intervals, R.string.basic_Prime_Numbers_Between_Two_Intervals_op));
        basicsList.add(new ProgramModel("Program to Check Armstrong Number", R.string.basic_Check_Armstrong_Number, R.string.basic_Check_Armstrong_Number_op));
        basicsList.add(new ProgramModel("Program to Display Armstrong Number Between Two Intervals", R.string.basic_Display_Armstrong_Number_Between_Two_Intervals, R.string.basic_Display_Armstrong_Number_Between_Two_Intervals_op));
        basicsList.add(new ProgramModel("Program to Display Factors of a Number", R.string.basic_Factors_of_a_Number, R.string.basic_Factors_of_a_Number_op));
        basicsList.add(new ProgramModel("Program to Count number of digits of an integer", R.string.basic_Count_number_of_digits_of_an_integer, R.string.basic_Count_number_of_digits_of_an_integer_op));

        //string programs
        ArrayList<ProgramModel> stringsList = new ArrayList<ProgramModel>();
        stringsList.add(new ProgramModel("Find length of String", R.string.strings_Find_length_of_String, R.string.strings_Find_length_of_String_op));
        stringsList.add(new ProgramModel("Compare Two Strings", R.string.strings_Compare_Two_Strings, R.string.strings_Compare_Two_Strings_op));
        stringsList.add(new ProgramModel("Reverse of String", R.string.strings_Reverse_of_String, R.string.strings_Reverse_of_String_op));
        stringsList.add(new ProgramModel("Count Freq. of Char", R.string.strings_Count_Freq_of_Char, R.string.strings_Count_Freq_of_Char_op));
        stringsList.add(new ProgramModel("Combined Two String", R.string.strings_Combined_Two_String, R.string.strings_Combined_Two_String_op));
        stringsList.add(new ProgramModel("Copy String", R.string.strings_Copy_String, R.string.strings_Copy_String_op));
        stringsList.add(new ProgramModel("Count Vowels in String", R.string.strings_Count_Vowels_in_String, R.string.strings_Count_Vowels_in_String_op));
        stringsList.add(new ProgramModel("Program to Find the Number of Vowels, Consonants, Digits and White Spaces in a String", R.string.strings_Find_the_Number_of_Vowels_in_a_String, R.string.strings_Find_the_Number_of_Vowels_in_a_String_op));
        stringsList.add(new ProgramModel("Program to Remove all Characters in a String Except Alphabets.", R.string.strings_Remove_all_Characters_in_a_String_Except_Alphabets, R.string.strings_Remove_all_Characters_in_a_String_Except_Alphabets_op));
        stringsList.add(new ProgramModel("Program to Sort Elements in Lexicographical Order (Dictionary Order)", R.string.strings_Sort_Elements_in_Lexicographical_Order, R.string.strings_Sort_Elements_in_Lexicographical_Order_op));

        //conversion programs
        ArrayList<ProgramModel> conversionList = new ArrayList<ProgramModel>();
        conversionList.add(new ProgramModel("Decimal to Binary Conversion", R.string.conversions_Decimal_to_Binary_Conversion, R.string.conversions_Decimal_to_Binary_Conversion_op));
        conversionList.add(new ProgramModel("Decimal to Octal", R.string.conversions_Decimal_to_Octal, R.string.conversions_Decimal_to_Octal_op));
        conversionList.add(new ProgramModel("Decimal to Hexadecimal", R.string.conversions_Decimal_to_Hexadecimal, R.string.conversions_Decimal_to_Hexadecimal_op));
        conversionList.add(new ProgramModel("Celsius to Fahrenheit", R.string.conversions_Celsius_to_Fahrenheit, R.string.conversions_Celsius_to_Fahrenheit_op));
        conversionList.add(new ProgramModel("Fahrenheit to Celsius", R.string.conversions_Fahrenheit_to_Celsius, R.string.conversions_Fahrenheit_to_Celsius_op));

        //arrays programs
        ArrayList<ProgramModel> arraysList = new ArrayList<ProgramModel>();
        arraysList.add(new ProgramModel("Sort Array Element", R.string.arrays_Sort_Array_Element, R.string.arrays_Sort_Array_Element_op));
        arraysList.add(new ProgramModel("Addition of Two Matrix", R.string.arrays_Addition_of_Two_Matrix, R.string.arrays_Addition_of_Two_Matrix_op));
        arraysList.add(new ProgramModel("Pass Array In Function", R.string.arrays_Pass_Array_In_Function, R.string.arrays_Pass_Array_In_Function_op));
        arraysList.add(new ProgramModel("Merge Any Array", R.string.arrays_Merge_Any_Array, R.string.arrays_Merge_Any_Array_op));
        arraysList.add(new ProgramModel("Insert Element in Array", R.string.arrays_Insert_Element_in_Array, R.string.arrays_Insert_Element_in_Array_op));
        arraysList.add(new ProgramModel("Delete Array Element", R.string.arrays_Delete_Array_Element, R.string.arrays_Delete_Array_Element_op));
        arraysList.add(new ProgramModel("Even-Odd Array Element", R.string.arrays_Even_Odd_Array_Element, R.string.arrays_Even_Odd_Array_Element_op));
        arraysList.add(new ProgramModel("Reverse Array Elements", R.string.arrays_Reverse_Array_Elements, R.string.arrays_Reverse_Array_Elements_op));
        arraysList.add(new ProgramModel("Sum of Array Elements", R.string.arrays_Sum_of_Array_Elements, R.string.arrays_Sum_of_Array_Elements_op));
        arraysList.add(new ProgramModel("Duplicate Array Elements", R.string.arrays_Duplicate_Array_Elemen, R.string.arrays_Duplicate_Array_Elemen_op));

        //patterns programs
        ArrayList<ProgramModel> patternsList = new ArrayList<ProgramModel>();
        patternsList.add(new ProgramModel("Triangle of Star", R.string.patterns_Triangle_of_Star, R.string.patterns_Triangle_of_Star_op));
        patternsList.add(new ProgramModel("Print Number Pattern", R.string.patterns_Print_Number_Pattern, R.string.patterns_Print_Number_Pattern_op));
        patternsList.add(new ProgramModel("Print Alphabet Pattern", R.string.patterns_Print_Alphabet_Pattern, R.string.patterns_Print_Alphabet_Pattern_op));
        patternsList.add(new ProgramModel("Print Number Series", R.string.patterns_Print_Number_Series, R.string.patterns_Print_Number_Series_op));
        patternsList.add(new ProgramModel("Diamond of Star", R.string.patterns_Diamond_of_Star, R.string.patterns_Diamond_of_Star_op));
        patternsList.add(new ProgramModel("Pascal Triangle", R.string.patterns_Pascal_Triangle, R.string.patterns_Pascal_Triangle_op));

        Map<String, List> masterMap = new HashMap<String, List>();
        masterMap.put("Basics", basicsList);
        masterMap.put("String Operations", stringsList);
        masterMap.put("Conversion", conversionList);
        masterMap.put("Arrays", arraysList);
        masterMap.put("Patterns", patternsList);


        return masterMap.get(key);
    }

}
