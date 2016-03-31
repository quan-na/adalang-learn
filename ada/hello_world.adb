with ADA.Text_IO;
use ADA.Text_IO; -- (3) use
with ADA.Integer_Text_IO;

procedure Hello_World is
   -- package IO renames Ada.Text_IO; -- (2) rename package
   package IntIO renames Ada.Integer_Text_IO;
   
   -- ada type
   --  elementary
   --   access
   --   scalar
   --    discrete
   --     enumeration
   --     integer
   --      signed
   --      modular
   --    real
   --     float
   --     fixed
   --      decimal
   --      ordinary
   --  composite
   --   array
   --   record
   --   protected
   --   task
   I: Integer := 0;
   -- types are defined on top of elementary
   -- and not compatible with each other
   type Int_With_Range is range 1..100;
   -- subtype is compatible with other subtypes
   -- or types if they have same base
   subtype Sub_Int is Int_With_Range range 10..50;
   -- derived type is type that inherite its base 's operations
   type Derived_Int is new Int_With_Range range 2..70;
   -- array?
   An_Array : array (1..100) of Integer;
   -- string?
   A_String : String (1..100);
   Constant_String : constant String := "Hello Ada !";
   -- function?
   -- why? function has only output params
   function Sum (A : in Integer; B : Integer) return Integer is
   begin
      return A + B;
   end;
   -- procedure?
   procedure Copy (A : in Integer; B : out Integer) is
   begin
      B := A;
   end;
   -- for loop demo
   X : Int_With_Range;
begin
   null; -- This line do nothing
   -- IO.Put_Line("Hello World!"); -- (2) rename package
   -- ADA.Text_IO.Put_Line("Hello World!"); -- (1) vanilla usage
   Put_Line("Hello World!"); -- (3) used

   Put("Input an integer number:");
   IntIO.Get(i);
   -- how to if-else?
   -- why the elsif, not elseif?
   if I < 0 then
      Put_Line("i is less than zero");
   elsif I > 10 then
      Put_Line("i is larger than ten");
   else
      Put_Line("i is something else.");
   end if;

   -- how to case?
   case I is
      when 1 => Put_Line("Take cover !");
      when 2 => Put_Line("Fire in the hole !");
      when 3 => Put_Line("Run for your life !");
      when others => Put_Line("We 're dead meat.");
   end case;
   
   -- why goto is kept?
   goto A_Label;
   Put_Line("This line is not called.");
<<A_Label>>
   
   -- while loop
   X := 1;
While_Loop :
    while X < 5 loop
       X := X + 1;
    end loop While_Loop;
   -- repeat loop
    X := 1;
Until_Loop :
    loop
       X := X + 1;
       exit Until_Loop when X > 5;
    end loop Until_Loop;
    -- for loop
For_Loop :
    for I in Integer range 1..19 loop
       IntIO.Put( I );
    end loop For_Loop;
    -- array loop
Array_Loop :
    for I in An_Array'Range loop
       IntIO.Put( I );
    end loop Array_Loop;

   -- how to return?
   return;
end Hello_World;
