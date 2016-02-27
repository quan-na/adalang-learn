with ADA.Text_IO;
use ADA.Text_IO; -- (3) use
with ADA.Integer_Text_IO;

procedure Hello_World is
   -- package IO renames Ada.Text_IO; -- (2) rename package
   package IntIO renames Ada.Integer_Text_IO;
   I: Integer := 0;
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

   -- how to return?
   return;
end Hello_World;
